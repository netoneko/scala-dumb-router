# Why Scala Dumb Router?

No reason. It's not production ready and never will be.

Features:
* simple Sinatra-like dsl (get, post & stuff)
* multiple servlet mappings to several servlet namespaces

Will be supported in future:
* helpers and helper functions
* more thorough testing of apps that were built on top of SDR

# Getting started with Scala Dumb Router

This project shows how to build a basic Scala Web application using sbt and [xsbt-web-plugin](https://github.com/JamesEarlDouglas/xsbt-web-plugin).  To get started, either clone this project or follow the steps below to recreate it.

## Starting from scratch

Clone repository and look at the basic structure of things.

Add a servlet:

*src/main/scala/dumb/router/example/SimpleServlet.scala*:

```scala
package dumb.router.example

import scala.xml.NodeSeq
import dumb.router._

class SimpleServlet extends Servlet {
  get("/", new Handler {
    def handle(request: Request, response: Response) = {
      val responseBody: NodeSeq = <html><body><h1>Hello, world!</h1></body></html>
      responseBody
    }
  })

  get("/hello", (request: Request, response: Response) => {
      val responseBody: NodeSeq = <html><body><h1>Hello!</h1></body></html>
      responseBody
    }
  )

  get("/hello/:name", (req: Request, _: Response) => {
    s"Hello, ${req getParameter "name"}!"
  })

}
```

*src/main/webapp/WEB-INF/web.xml*:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app
  xmlns="http://java.sun.com/xml/ns/javaee"
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd"
  version="2.5"
  >

  <servlet>
    <servlet-name>simple servlet</servlet-name>
    <servlet-class>dumb.router.example.SimpleServlet</servlet-class>
  </servlet>

  <servlet-mapping>
    <servlet-name>simple servlet</servlet-name>
    <url-pattern>/*</url-pattern>
  </servlet-mapping>

</web-app>
```

## Launching from sbt

From sbt, run the command `container:start`:

```
> container:start
[info] jetty-9.0.4.v20130625
[info] NO JSP Support for /, did not find org.apache.jasper.servlet.JspServlet
[info] Started SelectChannelConnector@0.0.0.0:8080
[success] Total time: 0 s, completed May 27, 2013 11:29:14 AM
>
```

The Web application is now running at http://localhost:8080/.  Take a look with a Web browser, or via curl:

```
$ curl -i localhost:8080
HTTP/1.1 200 OK
Content-Type: text/html; charset=utf-8
Content-Length: 48
Server: jetty-9.0.4.v20130625

<html><body><h1>Hello, world!</h1></body></html>
```

## Deploying to a servlet container

To build a WAR file suitable for deployment, run the command `package` from sbt:

```
> package
[success] Total time: 0 s, completed May 27, 2013 11:31:59 AM
> 
```

The WAR file can be found in *target/scala-2.10/xwp-template_2.10-0.1.0-SNAPSHOT.war*.
