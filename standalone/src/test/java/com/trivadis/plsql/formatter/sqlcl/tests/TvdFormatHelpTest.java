package com.trivadis.plsql.formatter.sqlcl.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TvdFormatHelpTest extends AbstractSqlclTest {
    final String expected = """
                            
            Trivadis PL/SQL & SQL Formatter (tvdformat), version XYZ
                            
            usage: tvdformat <rootPath> [options]
                            
            mandatory argument: (one of the following)
              <rootPath>      file or path to directory containing files to format (content will be replaced!)
              <config.json>   configuration file in JSON format (must end with .json)
              *               use * to format the SQLcl buffer
                            
            options:
              ext=<ext>       comma separated list of file extensions to process, e.g. ext=sql,pks,pkb
              mext=<ext>      comma separated list of markdown file extensions to process, e.g. ext=md,mdown
              xml=<file>      path to the file containing the xml file for advanced format settings
                              xml=default uses default advanced settings included in sqlcl
                              xml=embedded uses advanced settings defined in format.js
              arbori=<file>   path to the file containing the Arbori program for custom format settings
                              arbori=default uses default Arbori program included in sqlcl
              ignore=<file>   path to the file containing file patterns to ignore. Patterns are defined
                              per line. Each line represent a glob pattern. Empty lines and lines starting
                              with a hash sign (#) are ignored.
              --help, -h,     print this help screen and exit
              --version, -v   print version and exit

            """;

    @BeforeEach
    public void registerCommandBeforeTest() {
        runScript("--register");
        byteArrayOutputStream.reset();
    }

    @Test
    public void longArgument() {
        var actual = runCommand("tvdformat --help");
        Assertions.assertEquals(expected, actual.replaceAll(", version [^\\s]+", ", version XYZ"));
    }

    @Test
    public void shortArgument() {
        var actual = runCommand("tvdformat -h");
        Assertions.assertEquals(expected, actual.replaceAll(", version [^\\s]+", ", version XYZ"));
    }
}