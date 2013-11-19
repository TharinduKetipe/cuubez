/**
 * 
 */
package com.cuubez.auth.connector.impl;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.sql.Date;
import java.text.SimpleDateFormat;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cuubez.auth.connector.AccountingConnector;
import com.cuubez.auth.exception.AccountingConnectorException;
import com.cuubez.auth.model.AccountEntry;

/**
 * @author ruwan
 *
 */
public class FileAccountingConnector implements AccountingConnector {
	
	private static Log log = LogFactory.getLog(FileAccountingConnector.class);

	private static FileAccountingConnector fileAccountingConnector;
	
	private final String accountFileName = "audit.log";
	private RandomAccessFile randomAccessFile;
	private FileChannel fileChannel;
	private boolean failOnAccountError = true;
	private boolean guaranteedAccount = true;
	
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS_z");
	
	
	/**
	 * 
	 */
	public FileAccountingConnector(boolean failOnAccountError, boolean guaranteedAccount) throws AccountingConnectorException{
        this.failOnAccountError = failOnAccountError;
        this.guaranteedAccount = guaranteedAccount;
        
        try {
            randomAccessFile = new RandomAccessFile(accountFileName, "rw");
            fileChannel = randomAccessFile.getChannel();
            fileChannel.position(fileChannel.size());

        } catch (IOException e) {
            String errMsg = "Accounting file creation failed. - file name :" + accountFileName + ". " + e.getMessage();
            log.error(errMsg, e);
            if (failOnAccountError) {
                throw new AccountingConnectorException(errMsg, e);
            }
        }
	}

	/* (non-Javadoc)
	 * @see com.cuubez.auth.connector.AccountingConnector#account(com.cuubez.auth.model.AccountEntry)
	 */
	public void account(AccountEntry accountEntry) throws AccountingConnectorException {
        if (accountEntry == null) {
            return;
        }

        File f = new File(accountFileName);
        if(!f.exists()) {
            String errMsg = "Account failed. - : " + accountFileName + " does not exists. " + accountEntry;
            log.error(errMsg);
            if (failOnAccountError) {
                throw new AccountingConnectorException(errMsg);
            }
        }
        
        StringBuilder entry = new StringBuilder();
        entry.append(dateFormat.format(new Date(System.currentTimeMillis())));
        
        if (accountEntry.getUserId() != null) {
            entry.append(" [User:").append(accountEntry.getUserId()).append("]");
        }
        if (accountEntry.getRoleId() != null) {
            entry.append(" [Role:").append(accountEntry.getRoleId()).append("]");
        }
        if (accountEntry.getAccountAction() != null) {
            entry.append(" [Action:").append(accountEntry.getAccountAction()).append("]");
        }
        if (accountEntry.getServiceName() != null) {
            entry.append(" [Service:").append(accountEntry.getServiceName()).append("]");
        }
        entry.append(" " + accountEntry.getAccountOutcome()).append('\n') ;
        
        ByteBuffer buffer = ByteBuffer.allocateDirect(entry.toString().getBytes().length); 
        buffer.put(entry.toString().getBytes(Charset.forName("UTF-8")));
        buffer.flip();
        try {
            synchronized (fileChannel) {
                fileChannel.write(buffer);
                if(guaranteedAccount) {
                    fileChannel.force(false);
                }
            }
        } catch (IOException e) {            
            String errMsg = "Account failed. - :" + accountEntry + ". " + e.getMessage();
            log.error(errMsg, e);
            if (failOnAccountError) {
                throw new AccountingConnectorException(errMsg, e);
            }
        }
        
	}
	
	/**
	 * 
	 * @return
	 * @throws AccountingConnectorException 
	 */
	public static FileAccountingConnector getInstance(boolean failOnAccountError, boolean guaranteedAccount) throws AccountingConnectorException {
		if(fileAccountingConnector == null){
			fileAccountingConnector = new FileAccountingConnector(failOnAccountError, guaranteedAccount);
		}
		return fileAccountingConnector;
	}

}
