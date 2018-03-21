# digg-reader-dump

Command-line tool to take dump of your Digg Reader account. 

# Why?

Digg Reader does not allow me to take a dump of all my data, except the OPML file. My saved
articles, my diggs etc - neither it provides me a way to search in them. Thus I wrote this
tool in my spare time to dump all of them for me, and probably allow me to search in them
in future. Thus, I wrote this command-line tool in 2015 to do the same for me.

**Digg Reader is being shut down on March 26, 2018**. Save all your data.

# Hacking

Use Oracle JDK and Apache Maven to build the project:

```
$ mvn clean package
```

# Usage

You will need to extract the **cookie value** from your Digg Reader account using the Network
requests tab of your favorite browser. Just copy and paste the entire cookie (response header)
value as the last argument enclosed in single-quotes.

```
# To dump all saved articles
$ java -jar digg-reader-dump.jar saved -e <the cookie value>

# To export an OPML of all subscriptions
# java -jar digg-reader-dump.jar subs -c <the cookie value>
```

The following commands are available:

```
usage: $ java -jar digg-reader-dump.jar <command> [<args>]

The most commonly used digg commands are:
    all       Dump all articles
    diggs     Dump digged articles
    help      Display help information
    popular   Dump popular articles
    saved     Dump saved articles
    subs      Dump user subscriptions

See 'digg help <command>' for more information on a specific command.
```


# License

```
digg-reader-dump - CLI to export data out of Digg Reader
Copyright (c) 2015-2018, Sandeep Gupta

    http://sangupta.com/projects/digg-reader-dump

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
