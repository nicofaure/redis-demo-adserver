import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

    val appName = "redis-demo-adserver"
    val appVersion = "1.0"

    val adminDeps = Seq(
      javaCore,
      javaJdbc,
      javaJpa,
      cache,
      "org.slf4j" % "log4j-over-slf4j" % "1.7.6",
      "com.google.code.gson" % "gson" % "2.2.2",
      "commons-collections" % "commons-collections" % "3.2.1",
      "redis.clients" % "jedis" % "2.6.2",
      "org.scala-lang" % "scala-actors" % "2.10.0",
      "org.scala-lang" % "scala-reflect" % "2.10.0",
      "org.jdom" % "jdom" % "1.1",
      "org.springframework" % "spring-context" % "3.2.8.RELEASE",
      "commons-io" % "commons-io" % "2.4",
      "org.projectlombok" % "lombok" % "1.12.6",
      "org.hamcrest" % "hamcrest-all" % "1.3",
      "org.mockito" % "mockito-core" % "1.10.19" % "test"
   )



    val main = play.Project(appName, appVersion, adminDeps)
      .settings(projectSettings: _*)
      .settings(Keys.fork in (Test) := true,
            // uncomment this line to enable remote debugging
        //javaOptions in (Test) += "-Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=9998",
        javaOptions in (Test) += "-Xms512M",
        javaOptions in (Test) += "-Xmx2048M",
        javaOptions in (Test) += "-Xss1M",
        javaOptions in (Test) += "-XX:MaxPermSize=384M")
}