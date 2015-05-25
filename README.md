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
# drd subs -e <the cookie value>
```

