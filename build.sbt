val scalaV = "2.12.6"
val specs2V = "4.3.2"

libraryDependencies ++= Seq(
  "org.specs2" %% "specs2-core" % specs2V % "test"
)

scalaVersion := scalaV

// docs

enablePlugins(ParadoxMaterialThemePlugin, GhpagesPlugin)

paradoxMaterialTheme in Compile := {
  ParadoxMaterialTheme()
    // choose from https://jonas.github.io/paradox-material-theme/getting-started.html#changing-the-color-palette
    .withColor("light-green", "amber")
    // choose from https://jonas.github.io/paradox-material-theme/getting-started.html#adding-a-logo
    .withLogoIcon("info")
    .withCopyright("Copyleft © Johannes Rudolph")
    .withRepository(uri("https://github.com/jrudolph/knowledge-base"))
    .withSocial(
      uri("https://github.com/jrudolph"),
      uri("https://twitter.com/virtualvoid")
    )
}

paradoxProperties ++= Map(
  "github.base_url" -> (paradoxMaterialTheme in Compile).value.properties.getOrElse("repo", "")
)

makeSite / includeFilter := "*.html" | "*.css" | "*.png" | "*.png" | "*.js" | "*.woff" | "*.woff2" | "*.ttf" | "CNAME"
siteSourceDirectory := (Compile / paradox / target).value
makeSite := makeSite.dependsOn(Compile / paradox).value
ghpagesNoJekyll := true
git.remoteRepo := "git@github.com:jrudolph/knowledge-base.git"
//git.remoteRepo := "https://github.com/jrudolph/knowledge-base.git"