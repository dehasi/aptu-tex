package ru.spbau.krinkin.task1;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import ru.spbau.krinkin.task1.CompressingMessageWriter;
import ru.spbau.krinkin.task1.FileMessageReader;
import ru.spbau.krinkin.task1.Message;
import ru.spbau.krinkin.task1.IllegalMessageFormatException;

/**
 * Main program file. Program compress messages ({@link ru.spbau.krinkin.task1.Message Message}) from a file specified in first argument
 * and output to the System.out or a file specified in second argument
 *
 * @author Mike Krinkin, SPbAU 
 * @version 1.0
 */
public class Main {
	private static PrintStream m_outputStream = null;
	
	private static void redirectStdOutput(String fileName) throws FileNotFoundException {
		m_outputStream = new PrintStream(fileName);
		System.setOut(m_outputStream);
	}

	/**
	 * program entry point
	 *
	 * @param args command line parameters
	 * @throws IOException may throws IOException caused by input or output stream closing
	 */
	public static void main(String[] args) throws IOException {
		if (args.length == 0) {
			System.out.println("usage: program <input file name> [<output file name>]");
			System.exit(1);
		}
		
		FileMessageReader reader = null;
		CompressingMessageWriter writer = null;
		try {
			reader = new FileMessageReader(args[0]);
		
			if (args.length > 1) {
				redirectStdOutput(args[1]);
			}
		
			writer = new CompressingMessageWriter();
		
			Message msg = null;
			while ((msg = reader.readMessage()) != null) {
				writer.writeMessage(msg);
			}
		} catch (IllegalMessageFormatException e) {
			System.err.println(e.getMessage());
			e.printStackTrace(System.err);
		} catch (FileNotFoundException e) {
			System.err.println("input or output file dosen't exsists or not available");
			e.printStackTrace(System.err);
		} catch (IOException e) {
			e.printStackTrace(System.err);
		} finally {
			reader.close();
			writer.close();
			if (m_outputStream != null) {
				m_outputStream.close();
			}
		}
	}
}