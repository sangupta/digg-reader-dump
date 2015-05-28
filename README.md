# digg-reader-dump

Command-line tool to take dump of your Digg Reader account. 

# Why?

I use a many different feed readers along-side for different purposes, and 
I keep regularly taking a backup of my data. When I went to Digg Reader, it
does not allow me to take a dump of all my data, except the OPML file.

Thus, I wrote this command-line tool to do the same for me.

# Usage

```
# To dump all saved articles
$ drd saved -e <the cookie value>

# To export an OPML of all subscriptions
# drd subs -c <the cookie value>
```

The following commands are available:

```
usage: digg <command> [<args>]

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
Copyright (c) 2015, Sandeep Gupta

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
