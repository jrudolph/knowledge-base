# Discover Scala Code

Sometimes it's useful to be able to browse existing open source Scala code online to find examples
or just see how your dependencies work. Like for any more full-fledged IDE, the most important basic functionality
is "Go to definition" that cross-links source files (in the best case across libraries). In times when you don't have
an IDE at hand or you need to browse third party dependencies for which you don't have the tooling set up, it can be
nice to be able to discover Scala directly in the browser.

Nice-to-have functionality would be:

 * "Go To Definition" (in single files, across files, across library boundaries)
 * Global "Find all usages" - reverse of "Go To Definition" to find where symbols are used
 * Global Search for symbols / documentation ("Hoogle for Scala")
 * Custom linking setup (for a resolved dependency graph with possibly pulled up dependencies)
 * Code statistics - similar content as was provided in my 2014 [Scala In Numbers](https://2014.sca.land/) talk

The following are current best options:

## Metadoc

 * [Homepage](https://github.com/scalameta/metadoc)
 * [Demo](https://scalameta.org/metabrowse/)

The newest instance of such a tool. Uses the scalameta semantic API to render code and provide cross-linking.
The demo seems to contain at least the Scala library contents. No live version that allows to browse arbitrary
libraries.

## sxr - Scala X-Ray

 * [Homepage](https://github.com/sbt/sxr)
 * [Demo](http://harrah.github.io/browse/samples/index.html)

An old project of Mark Harrah which could be used to generate browsable source code which  seemed to work well enough
but is out of maintenance for a long time.

## scaladex

[Homepage](https://index.scala-lang.org/)

An index of Scala libraries. Has a search engine for content, tags, and versions but no way to actually browse or search
into the source code (aside from looking it up at Github).

## Other tools

 * [sourcegraph.com](https://sourcegraph.com/search?q=bindAndHandle+count%3A1000&patternType=literal#92) -
   seems to support searching for symbols and works well enough for that. Cross linking is supported in general but does not
   seem to work nicely for Scala.
 * [searchcode.com](https://searchcode.com/?q=bindAndHandle) - seems to work but unclear coverage
 * [elixir.bootlin.com](https://elixir.bootlin.com/linux/v5.8-rc4/A/ident/test) - seems to be the successor of LXR the
   Linux source code browsing tool.
 * [grepcode.com](https://grepcode.com) - one popular search engine, unfortunately extinct by now