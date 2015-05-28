package com.sangupta.diggdump;

import io.airlift.airline.Cli;
import io.airlift.airline.Cli.CliBuilder;
import io.airlift.airline.Help;

public class DiggReaderDumpMain {

	public static void main(String[] args) {
		@SuppressWarnings("unchecked")
		CliBuilder<Runnable> builder = Cli.<Runnable> builder("digg")
				.withDescription("Digg Reader Command line interface")
				.withDefaultCommand(Help.class)
				.withCommands(Help.class, DumpSavedArticles.class, DumpSubscriptions.class,
								DumpPopularArticles.class, DumpDiggedArticles.class);

		Cli<Runnable> parser = builder.build();

		Runnable runnable = parser.parse(args);
		runnable.run();
	}

}
