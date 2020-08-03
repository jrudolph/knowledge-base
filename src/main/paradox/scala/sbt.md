# SBT Tricks

## Don't pusblish docs on `publishLocal`

Often building docs is the slowest part of a `publishLocal` so it's recommended to turn it off if not needed.

[sbt/sbt#3537](https://github.com/sbt/sbt/issues/3537) recommends to set

```
set publishArtifact in (Compile, packageDoc) in ThisBuild := false
```

temporarily to avoid building docs for publishing.