package com.sangupta.diggdump;

import io.airlift.airline.Cli;
import io.airlift.airline.Cli.CliBuilder;
import io.airlift.airline.Help;

public class DiggReaderDumpMain {

	public static void main(String[] args) {
		args = new String[] { "saved", "-c", "_ga=GA1.2.1006232385.1518627856; _cb_ls=1; _cb=Bn2OKsBo1ZL4SuqV8; preferred_view=desktop; _xsrf=27519f09dc2742a8a2adef9842c90498; _chartbeat2=.1518627856296.1521472316812.0000000000000001.DpRPKeDxsa7HDleCcqBFcjxLBlL9v8; frontend.user=eyJ1c2VyX2lkIjoiMDNjN2IxOGY4OGZjNDg5OTk5M2M5NDZkYmI2ZjY0OTkiLCJncm91cHMiOlsicmVhZGVyIl0sInYiOiIxLjYuMCJ9|1521582171|2048f654011edbf8769bf60ee9a306a89b5335b5; frontend.ts=1521582172" };
		
		@SuppressWarnings("unchecked")
		CliBuilder<Runnable> builder = Cli.<Runnable> builder("digg")
				.withDescription("Digg Reader Command line interface")
				.withDefaultCommand(Help.class)
				.withCommands(Help.class, DumpSavedArticles.class, DumpSubscriptions.class,
								DumpPopularArticles.class, DumpDiggedArticles.class,
								DumpAllArticles.class);

		Cli<Runnable> parser = builder.build();

		Runnable runnable = parser.parse(args);
		runnable.run();
	}

}
