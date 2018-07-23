#log4j详解

在应用程序中添加日志记录总的来说基于三个目的：监视代码中变量的变化情况，周期性的记录到文件中供其他应用进行统计分析工作；跟踪代码运行时轨迹，作为日后审计的依据；担当集成开发环境中的调试器的作用，向文件或控制台打印代码的调试信息。

最普通的做法就是在代码中嵌入许多的打印语句，这些打印语句可以输出到控制台或文件中，比较好的做法就是构造一个日志操作类来封装此类操作，而不是让一系列的打印语句充斥了代码的主体。

##Log4j简介

Log4j是Apache的一个开放源代码项目，通过使用Log4j，我们可以控制日志信息输送的目的地是控制台、文件、GUI组件、甚至是套接口服务器、NT的事件记录器、UNIX Syslog守护进程等；我们也可以控制每一条日志的输出格式；通过定义每一条日志信息的级别，我们能够更加细致地控制日志的生成过程。最令人感兴趣的就是，这些可以通过一个配置文件来灵活地进行配置，而不需要修改应用的代码.

Log4j 是一个开源的日志记录组件，其产品已经相当的成熟，且使用非常的广泛。在工程中可以易用，方便等代替了 System.out 等打印语句，它是Java下最流行的日志输入工具，一些著名的开源项目，像spring、hibernate、struts都使用该工具作为日志输入工具，可以帮助调试（有时候debug是发挥不了作用的）和分析。 Log4j 的目标是：它允许开发人员以任意的精细程度控制哪些日志说明被输出。通过使用 外部的配置文件，可以在运行时配置它。 Log4j 的具体在 http://logging.apache.org/log4j/找到它（有使用的文档说明）。另 外，log4j 已经被转换成 C, C++, C#, Perl, Python, Ruby, 和 Eiffel 语言。一般情况下Log4j总是Apache Commons-logging一起用的，多了个东西不是更麻烦，而是更简单！

##Log4j组成：

Log4j中有三个主要的组件，它们分别是：Logger、Appender、Layout

Logger：Log4j 允许开发人员定义多个Logger，每个Logger拥有自己的名字，Logger之间通过名字来表明隶属关系。有一个Logger称为Root，它永远存在，且不能通过名字检索或引用，可以通过Logger.getRootLogger()方法获得，其它Logger通过 Logger.getLogger(String name)方法。（这是我们最常用的方法）

Appender：Appender则是用来指明将所有的log信息存放到什么地方，Log4j中支持多种appender，如 console、files、GUI components、NT Event Loggers等，一个Logger可以拥有多个Appender，也就是你既可以将Log信息输出到屏幕，同时存储到一个文件中。

Layout：Layout的作用是控制Log信息的输出方式，也就是格式化输出的信息。

Log4j中将要输出的Log信息定义了5种级别，依次为DEBUG、INFO、WARN、ERROR和FATAL，当输出时，只有级别高过配置中规定的级别的信息才能真正的输出，这样就很方便的来配置不同情况下要输出的内容，而不需要更改代码，比如配置输出级别为ERROR，那么程序的日志输出就只有ERROR信息和FATAL信息，而没有INFO信息和DEBUF信息。

##Log4j的配置文件：

虽然可以不用配置文件，而在程序中实现配置，但这种方法在如今的系统开发中显然是不可取的，能采用配置文件的地方一定一定要用配置文件。Log4j支持两种格式的配置文件：Log4j 支持两种配置文件格式，一种是 XML 格式的文件，一种是 Java 特性文件 lg4j.properties （键 = 值），properties文件简单易读，xml文件可以配置更多的功能（比如过滤），这个没有谁好谁坏，哪个适合用哪个，用着顺手的、能够融会贯通的就是最好的。


##语法介绍

```
* log4j.rootCategory=INFO, stdout , R
此句为将等级为INFO的日志信息输出到stdout和R这两个目的地，stdout和R的定义在下面的代码，可以任意起名。等级可分为OFF、FATAL、ERROR、WARN、INFO、DEBUG、ALL，如果配置OFF则不打出任何信息，如果配置为INFO这样只显示INFO, WARN, ERROR的log信息，而DEBUG信息不会被显示，具体讲解可参照第三部分定义配置文件中的logger。
* log4j.appender.stdout=org.apache.log4j.ConsoleAppender
此句为定义名为stdout的输出端是哪种类型，可以是org.apache.log4j.ConsoleAppender（控制台），org.apache.log4j.FileAppender（文件），org.apache.log4j.DailyRollingFileAppender（每天产生一个日志文件），org.apache.log4j.RollingFileAppender（文件大小到达指定尺寸的时候产生一个新的文件）org.apache.log4j.WriterAppender（将日志信息以流格式发送到任意指定的地方）
* log4j.appender.stdout.layout=org.apache.log4j.PatternLayout
此句为定义名为stdout的输出端的layout是哪种类型，可以是org.apache.log4j.HTMLLayout（以HTML表格形式布局），org.apache.log4j.PatternLayout（可以灵活地指定布局模式），org.apache.log4j.SimpleLayout（包含日志信息的级别和信息字符串），org.apache.log4j.TTCCLayout（包含日志产生的时间、线程、类别等等信息）
* log4j.appender.stdout.layout.ConversionPattern= [QC] %p [%t] %C.%M(%L) | %m%n
如果使用pattern布局就要指定的打印信息的具体格式ConversionPattern，打印参数如下：%m 输出代码中指定的消息；%M 输出打印该条日志的方法名；%p 输出优先级，即DEBUG，INFO，WARN，ERROR，FATAL；%r 输出自应用启动到输出该log信息耗费的毫秒数；%c 输出所属的类目，通常就是所在类的全名；%t 输出产生该日志事件的线程名；%n 输出一个回车换行符，Windows平台为"rn”，Unix平台为"n”；%d 输出日志时间点的日期或时间，默认格式为ISO8601，也可以在其后指定格式，比如：%d{yyyy-MM-dd HH:mm:ss,SSS}，输出类似：2002-10-18 22:10:28,921；%l 输出日志事件的发生位置，及在代码中的行数；[QC]是log信息的开头，可以为任意字符，一般为项目简称。输出示例[TS] DEBUG [main] AbstractBeanFactory.getBean(189) | Returning cached instance of singleton bean 'MyAutoProxy'

```
	