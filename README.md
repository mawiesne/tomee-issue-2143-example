## Project scope

A simple EAR-demo project to reproduce https://issues.apache.org/jira/browse/TOMEE-2143.

## Building the cookies...

`mvn clean install`

## Running the EAR 

From the module _tomee-ear_ launch a TomEE Plus 7.0.4 container via the bundled EAR file. Internally, it runs two WAR files: 

1. a JSF WEB-UI (the relevant project for TOMEE-2143) 
2. a RESTful web service (demonstrating a typical EAR use case). 

#### via Apache MyFaces (2.2.12)

`mvn tomee:run -Pjsf-myfaces`

#### via Mojarra (2.3.x)

Rebuild with 

`mvn clean install -Pjsf-mojarra`

and then

`mvn tomee:run -Pjsf-mojarra`

##  Re-producing cookies...

1. Login with one of the two accounts "admin" or "guest" at http://localhost:8787/cookie-webui. 
2. Produce at least one cookie
3. Logout
4. Login again with the same (or another) account you used in step 1.
5. Produce one or more cookies

The above cookie production steps work correctly via the 'jsf-mojarra' profile. 

The aforementioned steps work fine with the _jsf-mojarra_ profile. 
However, with the _jsf-myfaces_ profile, in step 3 this stacktrace is thrown and printed to the console... 

```
 java.lang.NullPointerException
	at org.apache.myfaces.cdi.view.ViewScopeContextImpl.destroyAllActive(ViewScopeContextImpl.java:229)
	at org.apache.myfaces.cdi.view.ViewScopeContextImpl.destroyAllActive(ViewScopeContextImpl.java:223)
	at org.apache.myfaces.cdi.view.ViewScopeBeanHolder.destroyBeansOnPreDestroy(ViewScopeBeanHolder.java:221)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:498)
	at org.apache.webbeans.intercept.LifecycleInterceptorInvocationContext.proceed(LifecycleInterceptorInvocationContext.java:103)
	...
```

...and the scenario fails ultimately in step 5 with

```
org.apache.myfaces.view.facelets.el.ContextAwareELException: javax.el.ELException: javax.enterprise.context.ContextNotActiveException: WebBeans context with scope type annotation @SessionScoped does not exist within current thread
	org.apache.myfaces.view.facelets.el.ContextAwareTagMethodExpression.invoke(ContextAwareTagMethodExpression.java:108)
	org.apache.myfaces.application.ActionListenerImpl.processAction(ActionListenerImpl.java:74)
	javax.faces.component.UICommand.broadcast(UICommand.java:120)
	javax.faces.component.UIViewRoot._broadcastAll(UIViewRoot.java:1174)
	javax.faces.component.UIViewRoot.broadcastEvents(UIViewRoot.java:365)
	javax.faces.component.UIViewRoot._process(UIViewRoot.java:1660)
	javax.faces.component.UIViewRoot.processApplication(UIViewRoot.java:864)
	org.apache.myfaces.lifecycle.InvokeApplicationExecutor.execute(InvokeApplicationExecutor.java:42)
	org.apache.myfaces.lifecycle.LifecycleImpl.executePhase(LifecycleImpl.java:196)
	org.apache.myfaces.lifecycle.LifecycleImpl.execute(LifecycleImpl.java:143)
	javax.faces.webapp.FacesServlet.service(FacesServlet.java:198)
	org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:52)
	org.ocpsoft.rewrite.servlet.RewriteFilter.doFilter(RewriteFilter.java:226)
	org.apache.shiro.web.servlet.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:112)
	org.ocpsoft.rewrite.servlet.impl.HttpRewriteResultHandler.handleResult(HttpRewriteResultHandler.java:42)
	org.ocpsoft.rewrite.servlet.RewriteFilter.rewrite(RewriteFilter.java:297)
	org.ocpsoft.rewrite.servlet.RewriteFilter.doFilter(RewriteFilter.java:198)
	org.apache.shiro.web.servlet.ProxiedFilterChain.doFilter(ProxiedFilterChain.java:61)
	org.apache.shiro.web.servlet.AdviceFilter.executeChain(AdviceFilter.java:108)
	org.apache.shiro.web.servlet.AdviceFilter.doFilterInternal(AdviceFilter.java:137)
	org.apache.shiro.web.servlet.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:125)
	org.apache.shiro.web.servlet.ProxiedFilterChain.doFilter(ProxiedFilterChain.java:66)
	org.apache.shiro.web.servlet.AbstractShiroFilter.executeChain(AbstractShiroFilter.java:449)
	org.apache.shiro.web.servlet.AbstractShiroFilter$1.call(AbstractShiroFilter.java:365)
	org.apache.shiro.subject.support.SubjectCallable.doCall(SubjectCallable.java:90)
	org.apache.shiro.subject.support.SubjectCallable.call(SubjectCallable.java:83)
	org.apache.shiro.subject.support.DelegatingSubject.execute(DelegatingSubject.java:387)
	org.apache.shiro.web.servlet.AbstractShiroFilter.doFilterInternal(AbstractShiroFilter.java:362)
	org.apache.shiro.web.servlet.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:125)
```
	
Please see https://issues.apache.org/jira/browse/TOMEE-2143 for further comments on details and observations. 