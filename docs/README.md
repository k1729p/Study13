<!DOCTYPE html>
<HTML>
<HEAD>
	<META charset="UTF-8">
</HEAD>
<BODY>
<a href="../../../tree/main/docs"><IMG src="images/ColorScheme.png" height="25" width="800"/></a>
<H2 id="contents">Study13 README Contents</H2>

<h3>Research the JBoss <a href="https://www.wildfly.org/">WildFly</a> application with EJB, JPA, JMS, CMT, BMT</h3>

<P><img src="images/MermaidFlowchart.png" height="160" width="340"/><br>
<img src="images/blackArrowUp.png">
<I>The flowchart with the WildFly application.</I></P>

<p>
The sections of this project:
<OL>
<LI><A href="#wildfly">WildFly Application Deploy</A>
<LI><A href="#e_j_b">Enterprise JavaBeans</A>
<UL>
<LI><A href="#e_j_b_tasks">EJB Example 'Tasks'</A></LI>
<LI><A href="#e_j_b_checks">EJB Example 'Checks'</A></LI>
<LI><A href="#e_j_b_interceptor">EJB Example 'Interceptor'</A></LI>
</UL></LI>
<LI><A href="#j_p_a">Java Persistence API</A>
<UL>
<LI><A href="#j_p_a_boxes">JPA Example 'Boxes'</A> 
(<I>one-to-one</I>, <I>one-to-many</I>, <I>many-to-one</I>, and <I>many-to-many</I> relationships)</LI>
<LI><A href="#j_p_a_units">JPA Example 'Units'</A> 
(<I>previous-next</I> and <I>parent-child</I> self-referential relationships)</LI>
<LI><A href="#j_p_a_levels">JPA Example 'Levels'</A> (hierarchical multilevel relationships)</LI>
</UL></LI>
<LI><A href="#j_m_s">Java Message Service</A>
<UL>
<LI><A href="#j_m_s_sync">JMS Example 'Synchronous Queue &amp; Topic'</A></LI>
<LI><A href="#j_m_s_async">JMS Example 'Asynchronous Queue &amp; Topic'</A></LI>
<LI><A href="#j_m_s_other_scenarios">Other JMS test scenarios</A></LI>
</UL></LI>
<LI><A href="#trans_c_m_t">Container-Managed Transactions</A></LI>
<LI><A href="#trans_b_m_t">Bean-Managed Transactions</A></LI>
</OL>
</p>

<P>Java source code. Packages in modules 'common', 'ejb', 'appclient':<BR/>
<img src="images/aquaHR-500.png"><BR/>
<img src="images/aquaSquare.png"> module 'common' application sources: 
<a href="https://github.com/k1729p/Study13/tree/master/common/src/main/java/kp/">kp</a><BR/>
<img src="images/aquaSquare.png"> module 'ejb' application sources: 
<a href="https://github.com/k1729p/Study13/tree/master/ejb/src/main/java/kp/">kp</a><BR/>
<img src="images/aquaSquare.png"> module 'appclient' application sources: 
<a href="https://github.com/k1729p/Study13/tree/master/appclient/src/main/java/kp/">kp</a><BR/>
<img src="images/aquaHR-500.png"></P>

<p>
<img src="images/yellowHR-500.png"><br>
<img src="images/yellowSquare.png">
    <a href="http://htmlpreview.github.io/?https://github.com/k1729p/Study13/blob/main/docs/apidocs/index.html">
	Java API Documentation</a><br>
<img src="images/yellowHR-500.png">
</p>

<HR/>
<H2 id="wildfly">❶ WildFly Application Deploy</H2>

<P>Action:<br>
<img src="images/orangeHR-500.png"><br>
<img src="images/orangeSquare.png"> 1. With batch file 
<a href="https://github.com/k1729p/Study13/blob/main/0_batch/01%20WildFly%20DeleteLog%20Startup.bat"> 
<I>"01 WildFly DeleteLog Startup.bat"</I></a> start the WildFly server.<br>
<img src="images/orangeSquare.png"> 2. With batch file 
<a href="https://github.com/k1729p/Study13/blob/main/0_batch/02%20MVN%20clean%20install%20deploy%20WildFly.bat"> 
<I>"02 MVN clean install deploy WildFly.bat"</I></a> build and deploy the application<br>
<img src="images/orangeSquare.png"><img src="images/spacer-32.png">on the WildFly server.<br>
<img src="images/orangeSquare.png"> 3. With the URL <a href="http://localhost:8080/Study13/">http://localhost:8080/Study13/</a> 
open in the web browser the '<i>home page</i>'.<br>
<img src="images/orangeHR-500.png"></P>

<P><img src="images/greenCircle.png"> 
1.1. The '<i>home page</i>' file <b>index.html</b>: 
<a href="https://github.com/k1729p/Study13/blob/main/web/src/main/webapp/index.html">HTML code</a>, 
<a href="http://htmlpreview.github.io/?https://github.com/k1729p/Study13/blob/main/web/src/main/webapp/index.html">
HTML preview</a>
</P>
<P>
<IMG src="images/ScreenshotHomePage.png" height="230" width="365"/><BR>
<img src="images/blackArrowUp.png">
<I>The screenshot of the home page.</I>
</P>

<P><img src="images/greenCircle.png"> 
1.2. The link to the WildFly Application Server <a href="http://localhost:8080/console">Administration Console</a>.
</P>

<a href="#top">Back to the top of the page</a>
<hr/>
<H2 id="e_j_b">❷ Enterprise JavaBeans</H2>

<P>Action:<BR/>
<img src="images/orangeHR-500.png"><BR/>
<img src="images/orangeSquare.png"> Go to page <a href="http://localhost:8080/Study13/">http://localhost:8080/Study13/</a> 
	and select "Enterprise JavaBeans".<BR/>
<img src="images/orangeHR-500.png"></P>

<P><IMG src="images/e_j_b_Controls.png" width="1200" /><BR/>
<img src="images/blackArrowUp.png">
<I>Screenshot from "Enterprise JavaBeans" page controls.</I></P>

<p>
The JSF page on the screenshot 
<a href="https://github.com/k1729p/Study13/blob/main/web/src/main/webapp/e_j_b.xhtml">'e_j_b.xhtml'</a> uses the beans:
<ul>
<li><a href="https://github.com/k1729p/Study13/blob/main/ejb/src/main/java/kp/e_j_b/controller/TasksManagedBean.java">
TasksManagedBean</a></li>
<li><a href="https://github.com/k1729p/Study13/blob/main/ejb/src/main/java/kp/e_j_b/controller/ChecksManagedBean.java">
ChecksManagedBean</a></li>
<li><a href="https://github.com/k1729p/Study13/blob/main/ejb/src/main/java/kp/e_j_b/controller/InterceptorsManagedBean.java">
InterceptorsManagedBean</a></li>
</ul>
</p>

<a href="#top">Back to the top of the page</a>
<H3 id="e_j_b_tasks"><img src="images/greenCircle.png"> 
2.1. EJB Example 'Tasks'</H3>
<p>There are three batches (each having five tasks) launched with managed executor service:
<OL>
  <LI>The button 'Tasks With Stateless' starts the tasks with the stateless session beans.</LI>
  <LI>The button 'Tasks With Singleton' starts the tasks with the singleton session beans.</LI>
  <LI>The button 'Tasks With Stateful' starts the tasks with the stateful session beans.</LI>
</OL>

<p><img src="images/greenCircle.png"> 
2.1.1. The tasks with the <b>stateless</b> session bean 
<a href="https://github.com/k1729p/Study13/blob/main/ejb/src/main/java/kp/e_j_b/remote/StaLeBean.java">StaLeBean</a>.
This is the implementation of the interface 
<a href="https://github.com/k1729p/Study13/blob/main/common/src/main/java/kp/e_j_b/remote/StaLe.java">StaLe</a>.<br>
The method 
<a href="https://github.com/k1729p/Study13/blob/main/ejb/src/main/java/kp/e_j_b/controller/TasksManagedBean.java#L85">
TasksManagedBean::researchStateless</a> submits the tasks to the 'ManagedExecutorService'.<br>
It is executed the method 
<a href="https://github.com/k1729p/Study13/blob/main/ejb/src/main/java/kp/e_j_b/CommonImpl.java#L42">CommonImpl::change</a>.<br>
</p>

<P>Action:<BR/>
<img src="images/orangeHR-500.png"><BR/>
<img src="images/orangeSquare.png"> Push the button "Tasks With Stateless" three times.</BR>
<img src="images/orangeHR-500.png"></P>

<p>Results:
<OL>
  <LI>It is evident that the bean stateless state (the value of the bean field) as a rule is not preserved.</LI>
  <LI>All five stateless beans (injected with @EJB) reference the same <b>proxy</b> object (hash codes are equal).</LI>
  <LI>All five stateless bean instances, used in launched tasks, were different objects (hash codes are not equal).</LI>
</OL>
</p>

<P><IMG src="images/TasksWithStateless.png" height="564" width="1014"/><BR>
<img src="images/blackArrowUp.png">
<I>Screen from three 'Tasks With Stateless' actions.</I></P>

<p><img src="images/greenCircle.png"> 
2.1.2. The tasks with the <b>singleton</b> session bean 
<a href="https://github.com/k1729p/Study13/blob/main/ejb/src/main/java/kp/e_j_b/remote/SingBean.java">SingBean</a>. 
This is the implementation of the interface 
<a href="https://github.com/k1729p/Study13/blob/main/common/src/main/java/kp/e_j_b/remote/Sing.java">Sing</a>.<br>
The method 
<a href="https://github.com/k1729p/Study13/blob/main/ejb/src/main/java/kp/e_j_b/controller/TasksManagedBean.java#L102">
TasksManagedBean::researchSingleton</a> submits the tasks to the 'ManagedExecutorService'.<br>
It is executed the method 
<a href="https://github.com/k1729p/Study13/blob/main/ejb/src/main/java/kp/e_j_b/CommonImpl.java#L42">CommonImpl::change</a>.<br>
</p>

<P>Action:<BR/>
<img src="images/orangeHR-500.png"><BR/>
<img src="images/orangeSquare.png"> Push the button "Tasks With Singleton" three times.</BR>
<img src="images/orangeHR-500.png"></P>

<p>Results:
<OL>
  <LI>From the bean hash code, it is evident that the singleton bean state (the value of the bean field) is always overwritten.</LI>
  <LI>All five singleton beans (injected with @EJB) reference the same <b>proxy</b> object (hash codes are equal).</LI>
  <LI>All five singleton bean instances, used in launched tasks, were the same object (hash codes are equal).</LI>
</OL>
</p>

<P><IMG src="images/TasksWithSingleton.png" height="565" width="1056"/><BR>
<img src="images/blackArrowUp.png">
<I>Screenshot from three 'Tasks With Singleton' actions.</I></P>

<p><img src="images/greenCircle.png"> 
2.1.3. The tasks with the <b>stateful</b> session bean 
<a href="https://github.com/k1729p/Study13/blob/main/common/src/main/java/kp/e_j_b/remote/StaFu.java">StaFu</a>. 
This is the implementation of the interface 
<a href="https://github.com/k1729p/Study13/blob/main/ejb/src/main/java/kp/e_j_b/remote/StaFuBean.java">StaFuBean</a>.<br>
The method 
<a href="https://github.com/k1729p/Study13/blob/main/ejb/src/main/java/kp/e_j_b/controller/TasksManagedBean.java#L119">
TasksManagedBean::researchStateful</a> submits the tasks to the 'ManagedExecutorService'.<br>
It is executed the method 
<a href="https://github.com/k1729p/Study13/blob/main/ejb/src/main/java/kp/e_j_b/CommonImpl.java#L42">CommonImpl::change</a>.<br>
</p>

<P>Action:<BR/>
<img src="images/orangeHR-500.png"><BR/>
<img src="images/orangeSquare.png"> Push the button "Tasks With Stateful" three times.</BR>
<img src="images/orangeHR-500.png"></P>

<p>Results:
<OL>
  <LI>The bean state (the value of the bean field) is always preserved.</LI>
  <LI>All five stateful beans (injected with @EJB) reference different <b>proxy</b> objects (hash codes are not equal).</LI>
  <LI>All five stateful bean instances, used in launched tasks, were different objects (hash codes are not equal).</LI>
</OL>
</p>

<P><IMG src="images/TasksWithStateful.png" height="563" width="1022"/><BR>
<img src="images/blackArrowUp.png">
<I>Screenshot from three 'Tasks With Stateful' actions.</I></P>

<p><img src="images/greenCircle.png"> 
2.1.4. The WildFly application client 
<a href="https://github.com/k1729p/Study13/blob/main/appclient/src/main/java/kp/client/ClientApplication.java">'ClientApplication'</a>.

<P>Action:<BR/>
<img src="images/orangeHR-500.png"><BR/>
<img src="images/orangeSquare.png"> With batch file 
<a href="https://github.com/k1729p/Study13/blob/main/0_batch/03%20Run%20WildFly%20Application%20Client.bat"> 
<I>"03 Run WildFly Application Client.bat"</I></a> run the WildFly application client.</BR>
<img src="images/orangeHR-500.png"></P>

<P><IMG src="images/WildFlyApplicationClient.png" height="60" width="330"/><BR>
<img src="images/blackArrowUp.png">
<I>The client console log.</I></P>

<P><IMG src="images/WildFlyServer.png" height="60" width="795"/><BR>
<img src="images/blackArrowUp.png">
<I>The server console log.</I></P>

<a href="#top">Back to the top of the page</a>
<H3 id="e_j_b_checks"><img src="images/greenCircle.png"> 
2.2. EJB Example 'Checks'</H3>

<P>Action:<BR/>
<img src="images/orangeHR-500.png"><BR/>
<img src="images/orangeSquare.png"> Push the button "Check Stateless &amp; Stateful &amp; Singleton".</BR>
<img src="images/orangeHR-500.png"></P>

<p><img src="images/greenCircle.png"> 
2.2.1. It is executed the method 
<a href="https://github.com/k1729p/Study13/blob/main/ejb/src/main/java/kp/e_j_b/controller/ChecksManagedBean.java#L89">
'ChecksManagedBean::researchStatelessStatefulSingleton'</a>.
</p>

<p><img src="images/greenCircle.png"> 
2.2.2. The bean instances invoked in this example:
<OL>
<LI>Local, no-interface view enterprise bean; injected with '@EJB' annotation; with class name 
<a href="https://github.com/k1729p/Study13/blob/main/ejb/src/main/java/kp/e_j_b/local/NoIntViBean.java">'NoIntViBean'</a>.</LI>
<LI>Local, stateless enterprise bean; injected with '@EJB' annotation; with class name 
<a href="https://github.com/k1729p/Study13/blob/main/ejb/src/main/java/kp/e_j_b/local/StaLeLocalBean.java">'StaLeLocalBean'</a>.</LI>
<LI>Remote, stateless enterprise bean; injected with '@EJB' annotation; with class name 
<a href="https://github.com/k1729p/Study13/blob/main/ejb/src/main/java/kp/e_j_b/remote/StaLeBean.java">'StaLeBean'</a>.</LI>
<LI>Remote, stateful enterprise bean; injected with '@EJB' annotation; with class name 
<a href="https://github.com/k1729p/Study13/blob/main/ejb/src/main/java/kp/e_j_b/remote/StaFuBean.java">'StaFuBean'</a>.</LI>
<LI>Remote, singleton enterprise bean; injected with '@EJB' annotation; with class name 
<a href="https://github.com/k1729p/Study13/blob/main/ejb/src/main/java/kp/e_j_b/remote/SingBean.java">'SingBean'</a>.</LI>
<LI>Local, no-interface view enterprise bean; injected with '@Inject' annotation; with class name 
<a href="https://github.com/k1729p/Study13/blob/main/ejb/src/main/java/kp/e_j_b/local/NoIntViBean.java">'NoIntViBean'</a>.</LI>
<LI>Local, stateless enterprise bean; injected with '@Inject' annotation; with class name 
<a href="https://github.com/k1729p/Study13/blob/main/ejb/src/main/java/kp/e_j_b/local/StaLeLocalBean.java">'StaLeLocalBean'</a>.</LI>
</OL>
</p>

<P><IMG src="images/CheckStatelessStatefulSingleton.png" height="334" width="977"/><BR>
<img src="images/blackArrowUp.png">
<I>Screenshot from 'Check Stateless &amp; Stateful &amp; Singleton' action.</I></P>

<a href="#top">Back to the top of the page</a>
<H3 id="e_j_b_interceptor"><img src="images/greenCircle.png"> 
2.3. EJB Example 'Interceptor'</H3>

<P>Action:<BR/>
<img src="images/orangeHR-500.png"><BR/>
<img src="images/orangeSquare.png"> Push the button "Interceptor".</BR>
<img src="images/orangeHR-500.png"></P>

<p><img src="images/greenCircle.png"> 
2.3.1. There were implemented interceptors for the time elapsed capture.<br>
In the <a href="https://github.com/k1729p/Study13/blob/main/ejb/src/main/java/kp/e_j_b/interceptors/TimeElapsedBean.java">
'TimeElapsedBean'</a> the interceptors were added to those methods:
<OL>
<LI><a href="https://github.com/k1729p/Study13/blob/main/ejb/src/main/java/kp/e_j_b/interceptors/TimeElapsedBean.java#L46">
'TimeElapsedBean::pausedMilli'</a> - this method stops for one millisecond.</LI>
<LI><a href="https://github.com/k1729p/Study13/blob/main/ejb/src/main/java/kp/e_j_b/interceptors/TimeElapsedBean.java#L33">
'TimeElapsedBean::pausedNano'</a> - this method stops for one nanosecond.</LI>
<LI><a href="https://github.com/k1729p/Study13/blob/main/ejb/src/main/java/kp/e_j_b/interceptors/TimeElapsedBean.java#L22">
'TimeElapsedBean::notPaused'</a> - this method is empty inside (has no body).</LI>
</OL>
</p>

<p><img src="images/greenCircle.png"> 
2.3.2. It is executed the method 
<a href="https://github.com/k1729p/Study13/blob/main/ejb/src/main/java/kp/e_j_b/controller/InterceptorsManagedBean.java#L34">
'InterceptorsManagedBean::researchInterceptor'</a>.
</p>

<P><IMG src="images/Interceptor.png" height="79" width="552"/><BR>
<img src="images/blackArrowUp.png">
<I>Screenshot from 'Interceptor' action.</I></P>

<a href="#top">Back to the top of the page</a>
<hr/>
<H2 id="j_p_a">❸ Java Persistence API</H2>
<p>
The data source 'Study13DS' uses an <b>H2 database</b> with the name 'study13' and in-memory mode.<BR/>
The configuration files:
<ul>
<li><a href="https://github.com/k1729p/Study13/blob/main/web/src/main/webapp/WEB-INF/study03-ds.xml">'study03-ds.xml'</a> in the 'web' module</li>
<li><a href="https://github.com/k1729p/Study13/blob/main/ejb/src/main/resources/META-INF/persistence.xml">'persistence.xml'</a> in the 'ejb' module</li>
</ul>
For CRUD actions there were used the criteria queries. There is a switch in the code to use the named queries.
</p>

<P>Action:<BR/>
<img src="images/orangeHR-500.png"><BR/>
<img src="images/orangeSquare.png"> Go to page <a href="http://localhost:8080/Study13/">http://localhost:8080/Study13/</a>
	and select "Java Persistence API".<BR/>
<img src="images/orangeHR-500.png"></P>

<P><IMG src="images/j_p_a_Controls.png" width="1200" /><BR/>
<img src="images/blackArrowUp.png">
<I>Screenshot from "Java Persistence API" page controls.</I></P>

<p>
The JSF page on the screenshot 
<a href="https://github.com/k1729p/Study13/blob/main/web/src/main/webapp/j_p_a.xhtml">'j_p_a.xhtml'</a> uses the beans:
<ul>
<li><a href="https://github.com/k1729p/Study13/blob/main/ejb/src/main/java/kp/j_p_a/controller/BoxBean.java">'BoxBean'</a></li>
<li><a href="https://github.com/k1729p/Study13/blob/main/ejb/src/main/java/kp/j_p_a/controller/UnitManagedBean.java">'UnitManagedBean'</a></li>
<li><a href="https://github.com/k1729p/Study13/blob/main/ejb/src/main/java/kp/j_p_a/controller/LevelManagedBean.java">'LevelManagedBean'</a></li>
</ul>
</p>

<a href="#top">Back to the top of the page</a>
<H3 id="j_p_a_boxes"><img src="images/greenCircle.png"> 
3.1. JPA Example 'Boxes'</H3>

<P>Action:<BR/>
<img src="images/orangeHR-500.png"><BR/>
<img src="images/orangeSquare.png"> 1. Push the button 'Create Box' four times.<BR/>
<img src="images/orangeSquare.png"> 2. Push the button 'Read Boxes'.<BR/>
<img src="images/orangeSquare.png"> 3. Push the button 'Update & Read Boxes'.<BR/>
<img src="images/orangeSquare.png"> 4. Push the button 'Delete Box' four times.<BR/>
<img src="images/orangeHR-500.png"></P>

<p><img src="images/greenCircle.png"> 
3.1.1. The entity classes for boxes:
<ul>
<li><a href="https://github.com/k1729p/Study13/blob/main/ejb/src/main/java/kp/j_p_a/domain/boxes/Box.java">'Box'</a></li>
<li><a href="https://github.com/k1729p/Study13/blob/main/ejb/src/main/java/kp/j_p_a/domain/boxes/CentralBox.java">'CentralBox'</a></li>
<li><a href="https://github.com/k1729p/Study13/blob/main/ejb/src/main/java/kp/j_p_a/domain/boxes/UpperBox.java">'UpperBox'</a></li>
<li><a href="https://github.com/k1729p/Study13/blob/main/ejb/src/main/java/kp/j_p_a/domain/boxes/LowerBox.java">'LowerBox'</a></li>
<li><a href="https://github.com/k1729p/Study13/blob/main/ejb/src/main/java/kp/j_p_a/domain/boxes/SingleBox.java">'SingleBox'</a></li>
<li><a href="https://github.com/k1729p/Study13/blob/main/ejb/src/main/java/kp/j_p_a/domain/boxes/MultiBox.java">'MultiBox'</a></li>
</ul>
</p>

<p><IMG src="images/DiagramBoxes.png" height="502" width="750"/><BR>
<img src="images/blackArrowUp.png">
<I>The relationships diagram for the boxes entity classes.</I></p>

<p><img src="images/greenCircle.png"> 
3.1.2. For the 'Create Box' (method 
<a href="https://github.com/k1729p/Study13/blob/main/ejb/src/main/java/kp/j_p_a/controller/BoxBean.java#L87">'BoxBean::createCentralBox'</a>
) only the first 3 calls were successful.<BR/>
The 4th call failed because the allowable creation maximum is 3 
<a href="https://github.com/k1729p/Study13/blob/main/ejb/src/main/java/kp/j_p_a/domain/boxes/CentralBox.java">'CentralBox'</a> objects.<BR/>
It was the transaction rollback on the 4th call.
</p>

<P><IMG src="images/CreateBox1.png" height="165" width="810"/><BR>
<img src="images/blackArrowUp.png">
<I>Screenshot from 1st 'Create Box' action.</I></P>

<P><IMG src="images/CreateBox2.png" height="185" width="810"/><BR>
<img src="images/blackArrowUp.png">
<I>Screenshot from 2nd 'Create Box' action.</I></P>

<P><IMG src="images/CreateBox3.png" height="205" width="810"/><BR>
<img src="images/blackArrowUp.png">
<I>Screenshot from 3rd 'Create Box' action.</I></P>

<P><IMG src="images/CreateBox4.png" height="35" width="705"/><BR>
<img src="images/blackArrowUp.png">
<I>Screenshot from 4th 'Create Box' action.</I></P>

<p><img src="images/greenCircle.png"> 
3.1.3. For the 'Read Boxes' (method 
<a href="https://github.com/k1729p/Study13/blob/main/ejb/src/main/java/kp/j_p_a/controller/BoxBean.java#L121">'BoxBean::readCentralBoxes'</a>
) report shows the 3 
<a href="https://github.com/k1729p/Study13/blob/main/ejb/src/main/java/kp/j_p_a/domain/boxes/CentralBox.java">'CentralBox'</a> 
objects and their dependencies.
</p>

<P><IMG src="images/ReadBoxes.png" height="665" width="810"/><BR>
<img src="images/blackArrowUp.png">
<I>Screenshot from 'Read Boxes' action.</I></P>

<p><img src="images/greenCircle.png"> 
3.1.4. For the 'Update & Read Boxes' (method 
<a href="https://github.com/k1729p/Study13/blob/main/ejb/src/main/java/kp/j_p_a/controller/BoxBean.java#L148">'BoxBean::updateCentralBoxes'</a>
) the 
<a href="https://github.com/k1729p/Study13/blob/main/ejb/src/main/java/kp/j_p_a/domain/boxes/CentralBox.java">'CentralBox'</a> 
field 'cardinalDirection' was changed.
</p>

<P><IMG src="images/UpdateAndReadBoxes.png" height="670" width="810"/><BR>
<img src="images/blackArrowUp.png">
<I>Screenshot from 'Update &amp; Read Boxes' action.</I></P>

<p><img src="images/greenCircle.png"> 
3.1.5. For the 'Delete Box' (method 
<a href="https://github.com/k1729p/Study13/blob/main/ejb/src/main/java/kp/j_p_a/controller/BoxBean.java#L188">'BoxBean::deleteCentralBox'</a>
) the 4th call failed because there were no more 
<a href="https://github.com/k1729p/Study13/blob/main/ejb/src/main/java/kp/j_p_a/domain/boxes/CentralBox.java">'CentralBox'</a> 
objects left after three 'Delete Box' calls.
</p>

<P><IMG src="images/DeleteBox1.png" height="35" width="627"/><BR>
<img src="images/blackArrowUp.png">
<I>Screenshot from 1st 'Delete Boxes' action.</I></P>

<P><IMG src="images/DeleteBox2.png" height="35" width="627"/><BR>
<img src="images/blackArrowUp.png">
<I>Screenshot from 2nd 'Delete Boxes' action.</I></P>

<P><IMG src="images/DeleteBox3.png" height="36" width="627"/><BR>
<img src="images/blackArrowUp.png">
<I>Screenshot from 3rd 'Delete Boxes' action.</I></P>

<P><IMG src="images/DeleteBox4.png" height="60" width="627"/><BR>
<img src="images/blackArrowUp.png">
<I>Screenshot from 4th 'Delete Boxes' action.</I></P>

<a href="#top">Back to the top of the page</a>
<H3 id="j_p_a_units"><img src="images/greenCircle.png"> 
3.2. JPA Example 'Units'</H3>

<P>Action:<BR/>
<img src="images/orangeHR-500.png"><BR/>
<img src="images/orangeSquare.png"> 1. Push the button 'Create-Read-Delete Units' the first time.<BR/>
<img src="images/orangeSquare.png"> 2. Push the button 'Create-Read-Delete Units' the second time.<BR/>
<img src="images/orangeHR-500.png"></P>

<P><img src="images/greenCircle.png"> 
3.2.1. For read actions there were used criteria queries (created with 'CriteriaBuilder').<BR/>

<P>The units entity classes have relationships:
<UL>
<LI><I>One-To-One</I> self-referential relationship between 'previous' and 'next' 
<a href="https://github.com/k1729p/Study13/blob/main/ejb/src/main/java/kp/j_p_a/domain/units/Unit.java">'Unit'</a> objects</LI>
<LI><I>Many-To-One</I> self-referential relationship between 'parent' and 'child' 
<a href="https://github.com/k1729p/Study13/blob/main/ejb/src/main/java/kp/j_p_a/domain/units/Unit.java">'Unit'</a> objects</LI>
<LI><I>One-To-Many</I> relationship between 
<a href="https://github.com/k1729p/Study13/blob/main/ejb/src/main/java/kp/j_p_a/domain/units/Unit.java">'Unit'</a> 
object and 4 
<a href="https://github.com/k1729p/Study13/blob/main/ejb/src/main/java/kp/j_p_a/domain/units/Side.java">'Side'</a> 
objects using enumeration 
<a href="https://github.com/k1729p/Study13/blob/main/ejb/src/main/java/kp/j_p_a/domain/components/CardinalDirection.java">
CardinalDirection</a></LI>
</UL></P>
	
<P><IMG src="images/DiagramUnits.png" height="200" width="600"/><BR>
<img src="images/blackArrowUp.png">
<I>The units relationships diagram.</I></P>

<P><img src="images/greenCircle.png"> 
3.2.2. With the first 'Create-Read-Delete Units' action the 
<a href="https://github.com/k1729p/Study13/blob/main/ejb/src/main/java/kp/j_p_a/domain/units/Unit.java">'Unit'</a> 
objects were created and persisted to the database. It executed the method 
<a href="https://github.com/k1729p/Study13/blob/main/ejb/src/main/java/kp/j_p_a/controller/UnitManagedBean.java#L92">
'UnitManagedBean::createUnits'</a>.
</P>

<P><IMG src="images/CreateReadDeleteUnits1.png" height="105" width="282"/><BR>
<img src="images/blackArrowUp.png">
<I>Screenshot from 1st 'Create-Read-Delete Units' action</I></P>

<P><img src="images/greenCircle.png"> 
3.2.3. With the second 'Create-Read-Delete Units' action the 
<a href="https://github.com/k1729p/Study13/blob/main/ejb/src/main/java/kp/j_p_a/domain/units/Unit.java">'Unit'</a> 
objects were read from the database, presented as a report, and removed from the database.<br>
It executed the method 
<a href="https://github.com/k1729p/Study13/blob/main/ejb/src/main/java/kp/j_p_a/controller/UnitManagedBean.java#L119">
'UnitManagedBean::readAndDeleteUnits'</a>.
</p>
<p>The presented report shows the results of the query.
<ul>
<li>for 3 <a href="https://github.com/k1729p/Study13/blob/main/ejb/src/main/java/kp/j_p_a/domain/units/Unit.java">'Unit'</a> 
objects there is a <I>previous - next</I> relationship: A -> B -> C.</li>
<li>for 4 <a href="https://github.com/k1729p/Study13/blob/main/ejb/src/main/java/kp/j_p_a/domain/units/Unit.java">'Unit'</a> 
objects there is a <I>parent - child</I> relationship: C -> (X, Y, Z).</li>
<li>for all 6 <a href="https://github.com/k1729p/Study13/blob/main/ejb/src/main/java/kp/j_p_a/domain/units/Unit.java">'Unit'</a> 
objects and 24 
<a href="https://github.com/k1729p/Study13/blob/main/ejb/src/main/java/kp/j_p_a/domain/units/Side.java">'Side'</a> 
objects there is a <I>one-to-many</I> relationship:<BR/>
every 
<a href="https://github.com/k1729p/Study13/blob/main/ejb/src/main/java/kp/j_p_a/domain/units/Unit.java">'Unit'</a> 
object has four unique 
<a href="https://github.com/k1729p/Study13/blob/main/ejb/src/main/java/kp/j_p_a/domain/units/Side.java">'Side'</a> objects.</li>
</ul>
</P>

<P><IMG src="images/CreateReadDeleteUnits2.png" height="242" width="776"/><BR>
<img src="images/blackArrowUp.png">
<I>Screenshot from 2nd 'Create-Read-Delete Units' action</I></P>

<a href="#top">Back to the top of the page</a>
<H3 id="j_p_a_levels"><img src="images/greenCircle.png"> 
3.3. JPA Example 'Levels'</H3>

<P>Actions:<BR/>
<img src="images/orangeHR-500.png"><BR/>
<img src="images/orangeSquare.png"> 1. Push the button 'Create-Read-Delete Levels' the first time.<BR/>
<img src="images/orangeSquare.png"> 2. Push the button 'Create-Read-Delete Levels' the second time.<BR/>
<img src="images/orangeHR-500.png"></P>

<p><img src="images/greenCircle.png"> 
3.3.1. For CRUD actions there were used criteria queries (created with 'CriteriaBuilder') with metamodels in package 
<a href="https://github.com/k1729p/Study13/tree/main/ejb/src/main/java/kp/j_p_a/domain/levels">'levels'</a>.
</p>
<p>
The entity classes for levels:
<ul>
<li><a href="https://github.com/k1729p/Study13/blob/main/ejb/src/main/java/kp/j_p_a/domain/levels/Level.java">'Level'</a></li>
<li><a href="https://github.com/k1729p/Study13/blob/main/ejb/src/main/java/kp/j_p_a/domain/levels/FirstLevel.java">'FirstLevel'</a></li>
<li><a href="https://github.com/k1729p/Study13/blob/main/ejb/src/main/java/kp/j_p_a/domain/levels/SecondLevel.java">'SecondLevel'</a></li>
<li><a href="https://github.com/k1729p/Study13/blob/main/ejb/src/main/java/kp/j_p_a/domain/levels/ThirdLevel.java">'ThirdLevel'</a></li>
<li><a href="https://github.com/k1729p/Study13/blob/main/ejb/src/main/java/kp/j_p_a/domain/levels/FourthLevel.java">'FourthLevel'</a></li>
</ul>
</p>

<P><IMG src="images/DiagramLevels.png" height="350" width="200"/><BR>
<img src="images/blackArrowUp.png">
<I>The levels relationships diagram. It is the 'One-To-Many' hierarchical relationships.</I></P>
</p>
<p><img src="images/greenCircle.png"> 
3.3.2. With the first 'Create-Read-Delete Levels' action the 
<a href="https://github.com/k1729p/Study13/blob/main/ejb/src/main/java/kp/j_p_a/domain/levels/Level.java">'Level'</a> 
objects were created and persisted to the database. It executed the method 
<a href="https://github.com/k1729p/Study13/blob/main/ejb/src/main/java/kp/j_p_a/controller/LevelManagedBean.java#L111">
'LevelManagedBean::createLevels'</a>.
</p>

<P><IMG src="images/CreateReadDeleteLevels1.png" height="57" width="107"/><BR>
<img src="images/blackArrowUp.png">
<I>Screenshot from 1st 'Create-Read-Delete Levels' action</I></P>

<p><img src="images/greenCircle.png"> 
3.3.3. With the second 'Create-Read-Delete Levels' action the 
<a href="https://github.com/k1729p/Study13/blob/main/ejb/src/main/java/kp/j_p_a/domain/levels/Level.java">'Level'</a> 
objects were read from the database, presented as a report, and removed from the database.<br>
The four methods were executed in a sequence:
<ol>
<li>the read method 
<a href="https://github.com/k1729p/Study13/blob/main/ejb/src/main/java/kp/j_p_a/controller/LevelManagedBean.java#L144">
'LevelManagedBean::readWithQueryUsingJoins'</a></li>
<li>the read method 
<a href="https://github.com/k1729p/Study13/blob/main/ejb/src/main/java/kp/j_p_a/controller/LevelManagedBean.java#L171">
'LevelManagedBean::readWithCriteriaUsingJoins'</a></li>
<li>the read method 
<a href="https://github.com/k1729p/Study13/blob/main/ejb/src/main/java/kp/j_p_a/controller/LevelManagedBean.java#L268">
'LevelManagedBean::readWithCriteriaUsingAggregateFunctions'</a></li>
<li>the delete method 
<a href="https://github.com/k1729p/Study13/blob/main/ejb/src/main/java/kp/j_p_a/controller/LevelManagedBean.java#L315">
'LevelManagedBean::deleteLevels'</a></li>
</ol>
</p>

<P>The presented report shows the results of the four queries:
<ol>
<li>the query using string with pattern "<B>SELECT ... FROM ... JOIN ... WHERE ... IN ...</B>"</li>
<li>the query using string with pattern "<B>SELECT ... FROM ... IN   ... WHERE ... IN ...</B>"</li>
<li>the query using criteria with '<B>join</B>'</li>
<li>the aggregate function query using criteria with '<B>groupBy</B>'</li>
</ol>
</P>
<P>For the 1st, 2nd, and 3rd queries, the result is identical.<BR/>
The result of the 4th query is the intersection of the '<B>having</B>' restriction and the '<B>where</B>' restriction.
</P>

<P><IMG src="images/CreateReadDeleteLevels2.png" height="703" width="938"/><BR>
<img src="images/blackArrowUp.png">
<I>Screenshot from 2nd 'Create-Read-Delete Levels' action</I></P>

<a href="#top">Back to the top of the page</a>
<hr/>
<H2 id="j_m_s">❹ Java Message Service</H2>
<p>
The initial JMS configuration in the WildFly server was done with the batch file 
<a href="https://github.com/k1729p/Study13/blob/main/0_batch/05%20CLI%20Config%20Queue%20%26%20Topic.bat">'06 CLI Config Queue & Topic.bat'</a>.<br> 
The changed batch script was used for creating the topics.
</p>

<P>Initial actions:<BR/>
<img src="images/orangeHR-500.png"><BR/>
<img src="images/orangeSquare.png"> 1. Go to page <a href="http://localhost:8080/Study13/">http://localhost:8080/Study13/</a>
	and select "Java Message Service".<BR/>
<img src="images/orangeHR-500.png"></P>

<P><IMG src="images/j_m_s_Controls.png" width="1200" /><BR/>
<img src="images/blackArrowUp.png">
<I>Screenshot from "Java Message Service" page controls.</I></P>

<p>
The JSF page  on the screenshot 
<a href="https://github.com/k1729p/Study13/blob/main/web/src/main/webapp/j_m_s.xhtml">'j_m_s.xhtml'</a> uses the beans:
</p>
<table style="border:solid"><tbody><tr>
<td style="border:solid"><a href="https://github.com/k1729p/Study13/blob/main/ejb/src/main/java/kp/j_m_s/sync/queue/QueueProducerSync.java">
'QueueProducerSync'</a></td>
<td style="border:solid"><a href="https://github.com/k1729p/Study13/blob/main/ejb/src/main/java/kp/j_m_s/sync/queue/QueueConsumerSync.java">
'QueueConsumerSync'</a></td>
</tr><tr>
<td style="border:solid"><a href="https://github.com/k1729p/Study13/blob/main/ejb/src/main/java/kp/j_m_s/sync/topic/TopicProducerSync.java">
'TopicProducerSync'</a></td>
<td style="border:solid"><a href="https://github.com/k1729p/Study13/blob/main/ejb/src/main/java/kp/j_m_s/sync/topic/TopicConsumerSync.java">
'TopicConsumerSync'</a></td>
</tr><tr>
<td style="border:solid"><a href="https://github.com/k1729p/Study13/blob/main/ejb/src/main/java/kp/j_m_s/async/queue/QueueProducerAsync.java">
'QueueProducerAsync'</a></td>
<td style="border:solid"></td>
</tr><tr>
<td style="border:solid"><a href="https://github.com/k1729p/Study13/blob/main/ejb/src/main/java/kp/j_m_s/async/topic/TopicProducerAsync.java">
'TopicProducerAsync'</a></td>
<td style="border:solid"></td>
</tr></tbody></table>

<br>

<P><IMG src="images/DiagramQueuesAndTopics.png" height="800" width="1100"/><BR>
<img src="images/blackArrowUp.png">
<I>Queues &amp; topics diagram.</I></P>

<a href="#top">Back to the top of the page</a>
<H3 id="j_m_s_sync"><img src="images/greenCircle.png"> 
4.1. JMS Example 'Synchronous Queue &amp; Topic'</H3>

<P>Actions:<BR/>
<img src="images/orangeHR-500.png"><BR/>
<img src="images/orangeSquare.png"> 1. Using the link 'Open Page in New Browser Tab' open the second browser tab.<BR/>
<img src="images/orangeSquare.png"> 2. In the <b>2nd tab</b> push the button "Queue Receive (Sync)".<BR/>
<img src="images/orangeSquare.png"> 3. In the <b>1st tab</b> push the button "Queue Send (Sync)".<BR/>
<img src="images/orangeSquare.png"> 4. In the <b>2nd tab</b> push the button "Topic Receive (Sync)".<BR/>
<img src="images/orangeSquare.png"> 5. In the <b>1st tab</b> push the button "Topic Send (Sync)".<BR/>
<img src="images/orangeSquare.png"> 6. In the <b>1st tab</b> click the link "Reload Report".<BR/>
<img src="images/orangeHR-500.png"></P>

<p><img src="images/greenCircle.png"> 
4.1.1. The synchronous queue methods
<ol>
<li><a href="https://github.com/k1729p/Study13/blob/main/ejb/src/main/java/kp/j_m_s/sync/queue/QueueProducerSync.java#L34">
'QueueProducerSync::sendQueueMessages'</a></li>
<li><a href="https://github.com/k1729p/Study13/blob/main/ejb/src/main/java/kp/j_m_s/sync/queue/QueueConsumerSync.java#L34">
'QueueConsumerSync::receiveQueueMessages'</a></li>
</ol>
</p>

<p><img src="images/greenCircle.png"> 
4.1.2. The synchronous topic methods
<ol>
<li><a href="https://github.com/k1729p/Study13/blob/main/ejb/src/main/java/kp/j_m_s/sync/topic/TopicProducerSync.java#L34">
'TopicProducerSync::sendTopicMessages'</a></li>
<li><a href="https://github.com/k1729p/Study13/blob/main/ejb/src/main/java/kp/j_m_s/sync/topic/TopicConsumerSync.java#L34">
'TopicConsumerSync::receiveTopicMessages'</a></li>
</ol>
</p>

<p><img src="images/greenCircle.png"> 
4.1.3. The methods used on reply queue:
<ul>
<li><a href="https://github.com/k1729p/Study13/blob/main/ejb/src/main/java/kp/j_m_s/reply/ReplyHelper.java#L31">
'ReplyHelper::processControlMessage'</a></li>
<li><a href="https://github.com/k1729p/Study13/blob/main/ejb/src/main/java/kp/j_m_s/reply/ReplyQueueMDB.java#L35">
'ReplyQueueMDB::onMessage'</a></li>
</ul>

<P><IMG src="images/SynchronousQueueAndTopicSendReceive.png" height="472" width="374"/><BR>
<img src="images/blackArrowUp.png">
<I>Screenshot from synchronous queue &amp; topic send/receive actions.</I></P>

<a href="#top">Back to the top of the page</a>
<H3 id="j_m_s_async"><img src="images/greenCircle.png"> 
4.2. JMS Example 'Asynchronous Queue &amp; Topic'</H3>

<P>Action:<BR/>
<img src="images/orangeHR-500.png"><BR/>
<img src="images/orangeSquare.png"> 1. Push the button "Queue Send (Async)".<BR/>
<img src="images/orangeSquare.png"> 2. Push the button "Topic Send (Async)".<BR/>
<img src="images/orangeSquare.png"> 3. Click the link "Reload Report".<BR/>
<img src="images/orangeHR-500.png"></P>

<p><img src="images/greenCircle.png"> 
4.2.1. The asynchronous queue methods:
<ol>
<li><a href="https://github.com/k1729p/Study13/blob/main/ejb/src/main/java/kp/j_m_s/async/queue/QueueProducerAsync.java#L34">
'QueueProducerAsync::sendQueueMessages'</a></li>
<li><a href="https://github.com/k1729p/Study13/blob/main/ejb/src/main/java/kp/j_m_s/async/queue/QueueConsumerMDB.java#L32">
'QueueConsumerMDB::onMessage'</a></li>
</ol>
</p>

<p><img src="images/greenCircle.png"> 
4.2.2. The asynchronous topic methods:
<ol>
<li><a href="https://github.com/k1729p/Study13/blob/main/ejb/src/main/java/kp/j_m_s/async/topic/TopicProducerAsync.java#L34">
'TopicProducerAsync::sendTopicMessages'</a></li>
<li><a href="https://github.com/k1729p/Study13/blob/main/ejb/src/main/java/kp/j_m_s/async/topic/TopicConsumerMDB.java#L32">
'TopicConsumerMDB::onMessage'</a></li>
</ol>

<p><img src="images/greenCircle.png"> 
4.2.3. The methods used on reply queue:
<ul>
<li><a href="https://github.com/k1729p/Study13/blob/main/ejb/src/main/java/kp/j_m_s/reply/ReplyHelper.java#L31">
'ReplyHelper::processControlMessage'</a></li>
<li><a href="https://github.com/k1729p/Study13/blob/main/ejb/src/main/java/kp/j_m_s/reply/ReplyQueueMDB.java#L35">
'ReplyQueueMDB::onMessage'</a></li>
</ul>

<P><IMG src="images/AsynchronousQueueAndTopicSendReceive.png" height="440" width="375"/><BR>
<img src="images/blackArrowUp.png">
<I>Screenshot from asynchronous queue &amp; topic send/receive actions.</I></P>

<a href="#top">Back to the top of the page</a>
<H3 id="j_m_s_other_scenarios"><img src="images/greenCircle.png"> 
4.3. Other JMS test scenarios</H3>

<p><img src="images/greenCircle.png"> 
4.3.1. "First send then receive" test scenario for synchronous queue:
<OL>
	<LI>Send to queue.</LI>
	<LI>Restart server.</LI>
	<LI>Receive from the queue.</LI>
	<LI>Result: messages were <B>not lost</B>.</LI>
</OL>
</p>
<p><img src="images/greenCircle.png"> 
4.3.2. "First send then receive" test scenario for synchronous topic:
<OL>
	<LI>Send to the topic.</LI>
	<LI>Receive from the topic.</LI>
	<LI>Because the receiver waits for messages (page hangs), repeat the sending to the topic.</LI>
	<LI>Result: messages sent in the 1st action were <B>lost</B>.</LI>
</OL>
</p>
<p><img src="images/greenCircle.png"> 
4.3.3. "Two consumers" test scenario for synchronous queue:
<OL>
	<LI>Open the 2nd & the 3rd tab.</LI>
	<LI>In the 2nd tab push the button "Queue Receive (Sync)".</LI>
	<LI>In the 3rd tab push the button "Queue Receive (Sync)".</LI>
	<LI>In the 1st tab push the button "Queue Send (Sync)" <B>two times</B> and click the link "Reload Report".</LI>
	<LI>Result: for two consumers two queues were used because this is point-to-point messaging.</LI>
</OL>
</p>
<p><img src="images/greenCircle.png"> 
4.3.4. "Two subscribers" test scenario for synchronous topic:
<OL>
	<LI>open the 2nd & the 3rd tab.</LI>
	<LI>In the 2nd tab push the button "Topic Receive (Sync)".</LI>
	<LI>In the 3rd tab push the button "Topic Receive (Sync)".</LI>
	<LI>In the 1st tab push the button "Queue Send (Sync)" <B>one time</B> and click link "Reload Report".</LI>
	<LI>Result: for two subscribers single topic was used because this is the publish/subscribe messaging.</LI>
</OL>
</p>

<a href="#top">Back to the top of the page</a>
<hr/>
<H2 id="trans_c_m_t">❺ Container-Managed Transactions</H2>

<p>
The queues initializer for the message-driven beans 
<a href="https://github.com/k1729p/Study13/blob/main/ejb/src/main/java/kp/trans_c_m_t/queues/ParcelQueuesInitializer.java">
'ParcelQueuesInitializer'</a>
creates the queues:
<ul>
<li>CreateParcelQueue</li>
<li>ConfirmCreateParcelQueue</li>
<li>ReadParcelQueue</li>
</ul>
</p>

<P>Action:<BR/>
<img src="images/orangeHR-500.png"><BR/>
<img src="images/orangeSquare.png"> 1. Go to page <a href="http://localhost:8080/Study13/">http://localhost:8080/Study13/</a>
	and select "Container-Managed Transactions".<BR/>
<img src="images/orangeSquare.png"> 2. Push the button "Send to 'CreateParcelQueue'" three times and the button "Reload Report".<BR/>
<img src="images/orangeSquare.png"> 3. Push the button "Send to 'ReadParcelQueue'" and the button "Reload Report".<BR/>
<img src="images/orangeSquare.png"> 4. Push the button "Show Audit" and the button "Reload Report".<BR/>
<img src="images/orangeHR-500.png"></P>

<P><IMG src="images/transactions_c_m_t_Controls.png" width="1200" /><BR/>
<img src="images/blackArrowUp.png">
<I>Screenshot from "Container-Managed Transactions" page controls.</I></P>

<p>
The JSF page on the screenshot 
<a href="https://github.com/k1729p/Study13/blob/main/web/src/main/webapp/transactions_c_m_t.xhtml">'transactions_c_m_t.xhtml'</a> uses the bean
<a href="https://github.com/k1729p/Study13/blob/main/ejb/src/main/java/kp/trans_c_m_t/controller/TransCmtManagedBean.java">
'TransCmtManagedBean'</a>
</p>

<p><img src="images/greenCircle.png"> 
5.1. The <a href="https://github.com/k1729p/Study13/blob/main/ejb/src/main/java/kp/trans_c_m_t/domain/Parcel.java">'Parcel'</a> 
objects are created with the method 
<a href="https://github.com/k1729p/Study13/blob/main/ejb/src/main/java/kp/trans_c_m_t/service/ParcelAdministratorBean.java#L49">
'ParcelAdministratorBean::create'</a> 
and read with the method
<a href="https://github.com/k1729p/Study13/blob/main/ejb/src/main/java/kp/trans_c_m_t/service/ParcelAdministratorBean.java#L68">
'ParcelAdministratorBean::read'</a>. 
The auditing is done with the methods 
<a href="https://github.com/k1729p/Study13/blob/main/ejb/src/main/java/kp/trans_c_m_t/service/AuditorBean.java#L42">
'AuditorBean::recordCreated'</a> and 
<a href="https://github.com/k1729p/Study13/blob/main/ejb/src/main/java/kp/trans_c_m_t/service/AuditorBean.java#L54">
'AuditorBean::recordApproved'</a>.
</p>
<p>
The <a href="https://github.com/k1729p/Study13/blob/main/ejb/src/main/java/kp/trans_c_m_t/domain/Parcel.java">'Parcel'</a> 
approving logic in method 
<a href="https://github.com/k1729p/Study13/blob/main/ejb/src/main/java/kp/trans_c_m_t/helper/Approver.java#L40">'Approver::approve'</a>:
<ul>
<li>Every newly created 
<a href="https://github.com/k1729p/Study13/blob/main/ejb/src/main/java/kp/trans_c_m_t/domain/Parcel.java">'Parcel'</a> 
object is validated.</li>
<li>The <a href="https://github.com/k1729p/Study13/blob/main/ejb/src/main/java/kp/trans_c_m_t/domain/Parcel.java">'Parcel'</a> 
with an odd id value (1, 3, 5, ...) is not approved and the transaction is marked for rollback.</li>
<li>The <a href="https://github.com/k1729p/Study13/blob/main/ejb/src/main/java/kp/trans_c_m_t/domain/Parcel.java">'Parcel'</a> 
with even id value (2, 4, 6, ...) is approved.</li>
</ul>
</p>
<P><IMG src="images/DiagramResearchCMT.png" height="1398" width="1200"/><BR>
<img src="images/blackArrowUp.png">
<I>The CMT sequence diagram.</I></P>

<p><img src="images/greenCircle.png"> 
5.2. For these three repeated actions "Send to 'CreateParcelQueue'" (method 
<a href="https://github.com/k1729p/Study13/blob/main/ejb/src/main/java/kp/trans_c_m_t/controller/TransCmtManagedBean.java#L62">
'TransCmtManagedBean::create'</a>'):
</p>

<OL>
<LI>The message from the 'CreateParcelQueue' is consumed and a new 
<a href="https://github.com/k1729p/Study13/blob/main/ejb/src/main/java/kp/trans_c_m_t/domain/Parcel.java">'Parcel'</a> 
is created.</LI>
<LI>The confirmation message is sent to the 'ConfirmCreateParcelQueue'.</LI>
<LI>The created 
<a href="https://github.com/k1729p/Study13/blob/main/ejb/src/main/java/kp/trans_c_m_t/domain/Parcel.java">'Parcel'</a> 
has an odd id value and is not approved.</LI>
<LI>Therefore the transaction is marked for rollback.</LI>
<LI>The message is <B>redelivered</B> to the 'CreateParcelQueue' and from this message a new 
<a href="https://github.com/k1729p/Study13/blob/main/ejb/src/main/java/kp/trans_c_m_t/domain/Parcel.java">'Parcel'</a> 
is created.</LI>
<LI>The confirmation message is sent to the 'ConfirmCreateParcelQueue'.</LI>
<LI>The created 
<a href="https://github.com/k1729p/Study13/blob/main/ejb/src/main/java/kp/trans_c_m_t/domain/Parcel.java">'Parcel'</a> 
has an even id value and is approved.</LI>
<LI>From the 'ConfirmCreateParcelQueue' is consumed only the 2nd confirmation message.</LI>
</OL>

<P><IMG src="images/CreateParcel1.png" height="196" width="575"/><BR>
<img src="images/blackArrowUp.png">
<I>Screenshot from 1st "Send to 'CreateParcelQueue'" action</I></P>

<P><IMG src="images/CreateParcel2.png" height="197" width="575"/><BR>
<img src="images/blackArrowUp.png">
<I>Screenshot from 2nd "Send to 'CreateParcelQueue'" action</I></P>

<P><IMG src="images/CreateParcel3.png" height="196" width="575"/><BR>
<img src="images/blackArrowUp.png">
<I>Screenshot from 3rd "Send to 'CreateParcelQueue'" action</I></P>

<p><img src="images/greenCircle.png"> 
5.3. The results of the action "Send to 'ReadParcelQueue'" (method 
<a href="https://github.com/k1729p/Study13/blob/main/ejb/src/main/java/kp/trans_c_m_t/controller/TransCmtManagedBean.java#L84">
'TransCmtManagedBean::read'</a>) show that all committed 
<a href="https://github.com/k1729p/Study13/blob/main/ejb/src/main/java/kp/trans_c_m_t/domain/Parcel.java">'Parcel'</a> 
objects in the database have even id values. This proves that all not approved 
<a href="https://github.com/k1729p/Study13/blob/main/ejb/src/main/java/kp/trans_c_m_t/domain/Parcel.java">'Parcel'</a> 
objects were rolled back.</P>

<P><IMG src="images/ReadParcels.png" height="106" width="575"/><BR>
<img src="images/blackArrowUp.png">
<I>Screenshot from "Send to 'ReadParcelQueue'" action</I>.</P>

<p><img src="images/greenCircle.png"> 
5.4. The auditing in the action "Show Audit" (method 
<a href="https://github.com/k1729p/Study13/blob/main/ejb/src/main/java/kp/trans_c_m_t/controller/TransCmtManagedBean.java#L97">
'TransCmtManagedBean::showAudit'</a>) is always processed in a new transaction. 
Therefore the audit information was not lost when the main transaction was rolled back.</P>

<P><IMG src="images/ShowAudit.png" height="34" width="575"/><BR>
<img src="images/blackArrowUp.png">
<I>Screenshot from "Show Audit" action</I></P>

<a href="#top">Back to the top of the page</a>
<hr/>
<H2 id="trans_b_m_t">❻ Bean-Managed Transactions</H2>

<table style="border:solid"><tbody><tr><td style="border:solid"><i>
In a stateless session bean with bean-managed transactions,<BR/>
a business method must commit or roll back a transaction before returning.<BR/>
However, a stateful session bean does not have this restriction.<BR/>
In a stateful session bean with a JTA transaction,<BR/>
the association between the bean instance and the transaction is retained across multiple client calls.<BR/>
Even if each business method called by the client opens and closes the database connection,<BR/>
the association is retained until the instance completes the transaction.
</i></td></tr></tbody></table>

<P>Action:<BR/>
<img src="images/orangeHR-500.png"><BR/>
<img src="images/orangeSquare.png"> 1. Go to the page <a href="http://localhost:8080/Study13/">http://localhost:8080/Study13/</a>
	and select "Bean-Managed Transactions".<BR/>
<img src="images/orangeSquare.png"> 2. Push the button "Create Capsule" two times.<BR/>
<img src="images/orangeSquare.png"> 3. Push the button "Commit Transaction" and the button "Read Capsule".<BR/>
<img src="images/orangeSquare.png"> 4. Push the button "Create Capsule" and the button "Read Capsule".<BR/>
<img src="images/orangeSquare.png"><img src="images/spacer-32.png">
This step has created a not committed 'Capsule' object.<BR/>
<img src="images/orangeSquare.png"> 5. Push the button "Rollback Transaction" and the button "Read Capsule".<BR/>
<img src="images/orangeSquare.png"><img src="images/spacer-32.png">
It was rolled back to the last commit (point 3. above).<BR/>
<img src="images/orangeSquare.png"> 6. Push the button "Delete Capsule" and the button "Read Capsule".<BR/>
<img src="images/orangeSquare.png"><img src="images/spacer-32.png">
The "Delete Capsule" action deletes the last created 'Capsule'<BR/>
<img src="images/orangeSquare.png"> 7. Push the button "Rollback Transaction" and the button "Read Capsule".<BR/>
<img src="images/orangeSquare.png"><img src="images/spacer-32.png">
Again, it was rolled back to the last commit (point 3. above).<BR/>
<img src="images/orangeHR-500.png"></P>

<P><IMG src="images/transactions_b_m_t_Controls.png" width="1200" /><BR/>
<img src="images/blackArrowUp.png">
<I>Screenshot from "Bean-Managed Transactions" page controls.</I></P>

<p>
The JSF page on the screenshot 
<a href="https://github.com/k1729p/Study13/blob/main/web/src/main/webapp/transactions_b_m_t.xhtml">'transactions_b_m_t.xhtml'</a> uses the bean
<a href="https://github.com/k1729p/Study13/blob/main/ejb/src/main/java/kp/trans_b_m_t/controller/TransBmtManagedBean.java">
'TransBmtManagedBean'</a>
</p>

<p><img src="images/greenCircle.png"> 
6.1. The methods of the <b>stateful</b> session bean 
<a href="https://github.com/k1729p/Study13/blob/main/ejb/src/main/java/kp/trans_b_m_t/service/CapsuleAdministratorBean.java">
'CapsuleAdministratorBean'</a>:
<ul>
<li><a href="https://github.com/k1729p/Study13/blob/main/ejb/src/main/java/kp/trans_b_m_t/service/CapsuleAdministratorBean.java#L68">
'CapsuleAdministratorBean::create'</a></li>
<li><a href="https://github.com/k1729p/Study13/blob/main/ejb/src/main/java/kp/trans_b_m_t/service/CapsuleAdministratorBean.java#L95">
'CapsuleAdministratorBean::read'</a></li>
<li><a href="https://github.com/k1729p/Study13/blob/main/ejb/src/main/java/kp/trans_b_m_t/service/CapsuleAdministratorBean.java#L126">
'CapsuleAdministratorBean::delete'</a></li>
<li><a href="https://github.com/k1729p/Study13/blob/main/ejb/src/main/java/kp/trans_b_m_t/service/CapsuleAdministratorBean.java#L165">
'CapsuleAdministratorBean::commit'</a></li>
<li><a href="https://github.com/k1729p/Study13/blob/main/ejb/src/main/java/kp/trans_b_m_t/service/CapsuleAdministratorBean.java#L186">
'CapsuleAdministratorBean::rollback'</a></li>
</ul>
</p>

<P><IMG src="images/DiagramResearchBMT.png" height="237" width="708"/><BR>
<img src="images/blackArrowUp.png">
<I>BMT class diagram.</I></P>

<p><img src="images/greenCircle.png"> 
6.2. Creating the 
<a href="https://github.com/k1729p/Study13/blob/main/ejb/src/main/java/kp/trans_b_m_t/domain/Capsule.java">'Capsule'</a> 
object and committing the transaction.
<p>

<P><IMG src="images/CreateAndCommitCapsule.png" height="128" width="570"/><BR>
<img src="images/blackArrowUp.png">
<I>Screenshot from  actions: 1st "Create Capsule", 2nd "Create Capsule", "Commit Transaction", "Read Capsule"</I></P>

<p><img src="images/greenCircle.png"> 
6.3. Creating the 
<a href="https://github.com/k1729p/Study13/blob/main/ejb/src/main/java/kp/trans_b_m_t/domain/Capsule.java">'Capsule'</a> 
object and rollbacking the transaction.
<p>

<P><IMG src="images/CreateCapsule.png" height="105" width="570"/><BR>
<img src="images/blackArrowUp.png">
<I>Screenshot from  actions: 3rd "Create Capsule", "Read Capsule"</I></P>

<P><IMG src="images/RollbackCreatedCapsule.png" height="82" width="570"/><BR>
<img src="images/blackArrowUp.png">
<I>Screenshot from  actions: 1st "Rollback Transaction", "Read Capsule"</I></P>

<p><img src="images/greenCircle.png"> 
6.4. Deleting the 
<a href="https://github.com/k1729p/Study13/blob/main/ejb/src/main/java/kp/trans_b_m_t/domain/Capsule.java">'Capsule'</a> 
object and rollbacking the transaction.
<p>

<P><IMG src="images/DeleteCapsule.png" height="59" width="570"/><BR>
<img src="images/blackArrowUp.png">
<I>Screenshot from  actions: "Delete Capsule", "Read Capsule"</I></P>

<P><IMG src="images/RollbackDeletedCapsule.png" height="83" width="570"/><BR>
<img src="images/blackArrowUp.png">
<I>Screenshot from  actions: 2nd "Rollback Transaction", "Read Capsule"</I></P>

<a href="#top">Back to the top of the page</a>
<HR/>
</BODY>
</HTML>