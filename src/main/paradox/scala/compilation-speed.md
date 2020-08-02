# Scala Compilation Speed

Scala has been somewhat notorious for long compile times. This has improved a lot over the years. Compared to other
high-level language compilers, it's probably not too bad (though the usual comparison with Java hurts).

Scala has a working incremental compiler, so when used from sbt (or other build systems), recompilation is not
a big issue in most cases.

## Potential culprits for slow compile times

In user code (in no particular order):

 * Heavy use of implicits
 * Type inference (adding explicit types to public members can help a lot)
 * Macros (can disturb incremental compilation when macros are defined in an internal dependency and that changes)
 * Big Modules (if you avoid circular dependencies between files you can often restructure code into more modules to
   help with parallelism)
 * Mixed Mode projects containing Java and Scala files (at least that's what I observed in akka-http that sbt cannot infer
   well enough what to recompile and incremental compilation is slower than a full recompile)

In scalac's compiler architecture:

 * Mostly single-threaded execution (outline compilation might be a remedy in the future)

## Other Scala compilers with a focus on speed

 * [triplequote hydra](https://www.triplequote.com/hydra/) - a commercial parallel Scala compiler implementation
 * [twitter rsc](https://github.com/twitter/rsc) - "Reasonable Scala compiler (also known as Rsc) is an experimental Scala compiler focused on compilation speed."
 * [gkossakowski's kentucky mule](https://github.com/gkossakowski/kentuckymule) - "an exploration of an alternative architecture for Scala compiler design (specifically typechecker) with a focus on speed"
 * [dotty](http://dotty.epfl.ch/) - The next-generation compiler for Scala 3 - a simpler type-system and a new compiler architecture
   may or may not bring performance improvements (haven't seen benchmarks)

## Tools

 * [scalac-profiling](https://scalacenter.github.io/scalac-profiling/) - "aims at providing the tools to understand and profile your Scala projects"
 * [scalafix ExplicitResultTypes](https://scalacenter.github.io/scalafix/docs/rules/ExplicitResultTypes.html) refactoring - explicit result type will
   help compilation speed already now but it's also mandatory for outline compilation (when that is available)
 * [bloop](https://scalacenter.github.io/bloop/) - "One toolchain, one build server, one developer experience"