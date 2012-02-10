package ru.spbau.krinkin.task1;

import java.io.IOException;

import ru.spbau.krinkin.task1.ConsoleMessageWriter;
import ru.spbau.krinkin.task1.Message;

/**
 * class ComressingMessageWriter extends {@link ru.spbau.krinkin.task1.ConsoleMessageWriter ConsoleMessageWriter}, but
 * compress two messages in one {@link ru.spbau.krinkin.task1.Message Message} then outputs to a specified file
 *
 * @author Mike Krinkin, SPbAU 
 * @version 1.0
 */
public class CompressingMessageWriter extends ConsoleMessageWriter {
	private Message m_lastMessage = null;

	/**
	 * method merges pairs of {@link ru.spbau.krinkin.task1.Message Message} and then puts union in System.out
	 *
	 * @param msg {@link ru.spbau.krinkin.task1.Message Message}
	 * @throws IOException
	 */
	public void writeMessage(Message msg) throws IOException {
		if (m_lastMessage != null) {
			m_lastMessage.append(msg);
			super.writeMessage(m_lastMessage);
			m_lastMessage = null;
		} else {
			m_lastMessage = msg;
		}
	}
	
	/**
	 * method flush last {@link ru.spbau.krinkin.task1.Message Message} and drops message counter
	 * (see {@link ru.spbau.krinkin.task1.ConsoleMessageWriter ConsoleMessageWriter})
	 *
	 * @throws IOException
	 */
	public void close() throws IOException {
		if (m_lastMessage != null) {
			super.writeMessage(m_lastMessage);
		}
		super.close();
	}
}