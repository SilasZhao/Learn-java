# Learn-java
mooc笔记：
Lamdba 表达形式：
		没有参数
			eg（）-> sout(“hello world”);
		有一个参数
		 	不用加括号
		有两个及以上参数
			必须加括号
		函数有多条语句
			箭头后要加大括号
		对参数的显示声明
			可选
	函数式接口
		接口中只有一个方法
		注解@FunctionInterface
		函数式接口的抽象方法签名：函数描述符
	方法引用：
		方法引用是用来直接访问类或者实例的已经存在的方法或者构造方法
		调用特定方法的Lambda表达式的一种快捷写法，可以让你重复使用现有的方法定义，并像Lambda表达式一样传递他们。
		语法：
			目标引用::方法名
		
		1，引用静态方法：		
			Consumer <String> consumer1 = (String number) -> Integer.parseInt(number)
			<->
			Consumer <String> consumer2 = Integer::parseInt

		2, 指向任意实例方法的方法引用：
			Consumer <String> consumer1 = (String str) -> str.length();
			<->
			Consumer <String> consumer2 = String::length;

		3, 指向现有对象的实例方法的方法引用：
			StringBuilder stringBuilder = new StringBuilder();
			Consumer <String> consumer1 = (String str) -> stringBuilder.append(str);
			<->
			Consumer <String> consumer2 = stringBuilde :: append;
		4, 指向构造方法：
			Class：：new

Stream 流:
		从支持数据处理操作的源的元素序列

	流与集合的区别：
		时间与空间（流倾向于计算，而集合倾向于存储）
		流只能遍历一次
		集合是外部迭代，流是内部迭代
	流的组成：
		Cart    -> fileter -> sorted -> map  -> collect
		数据源 ->		       中间操作	   	->终端操作


	收集器简介
	collect/Collector/Collectors
			接口	/实现了collector的类
	预定义收集器功能
		将流元素约合和汇总为一个值
		将流元素分组
		将流元素分区

	规约（reduce）：将stream流中元素转换成一个值
		--初始值（identity）
		--计算逻辑（accumulator）
		--并行执行时多个部分结果的合并方式（combiner） 
	汇总（collect）：将stream流中元素转换成一个容器

资源关闭
	垃圾回收（GC）的特点
		垃圾回收机制只负责回收堆内存资源，不会回收任何物理资源
		程序无法精确控制垃圾回收动作的具体发生时间内
		在垃圾回收之前，总会调用它的finalize方法
	需要回收的商品
		文件/流资源
		套接字资源
		数据库连接资源
Guava
	Optional -- if there exists some null value
	immutableSet
	MultiSet
	IO:
		Souce -- read
		Sink -- write
线程池：
	--可以事先创建若干可执行的线程放入一个池（容器）中，需要的时候从池中获取线程不用自行创建，使用完毕不需要销毁线程而是直接放回池中，从而减少创建和销毁线程对象的开销。
	好处：
		降低资源消耗
		提高相应速度
		提高线程的可管理性
	简单线程池的设定：
		开启/初始化/关闭
		功能：获取线程，归还线程？  
		问题：
			创建多少线程？
			线程不够用怎么办？
	处理流程：
		提交任务 -> 核心线程池是否已满？ -yes-> 阻塞序列是否已满？ -yes-> 线程池是否已满？ -yes-> 按照饱和策略处理多余
						-no->创建线程执行任务	 -no->将任务储存在阻塞队列  -no->创建新线程执行任务
		注意：先查看阻塞序列再决定要不要创建新的线程
		阻塞队列：	
			支持阻塞插入
			支持阻塞移除
			类型：
				无界队列
				有界队列
				同步移交队列 (无储存能力) 
		饱和策略：
			AbortPolicy (defaulted): throw error when the queue is full
			DIscardPolicy: just discard the task
			DiscardOldestPolicy: discard the oldest task
			CallerRunsPolicy: caller can run the task (in main)
		常用线程池：
			newCachedThreadPool (unlimited number of thread)
			newFixedThreadPool (limited thread, unlimited queue)
			newSingleThreadExecutor (only one thread)
	线程池的状态
		Running -shotDown()-> Shutdown -队列为空，线程中执行的任务为空->Tiying -调用terminated()-> terminated
				-shutDownNow()->stop   -线程池中执行的任务为空------->
		注：shutDown()是不再接受新的任务
			shutDownNow()是不再接受新任务，并且丢弃队列中的任务，中断正在执行的任务。

Lombok:
	运行时解析（java spring中的一些利用反射的注释）
	编译时解析（lombok）

@NotBlank，@NotEmpty区别：
	""是blank也是empty
	" "不是empty，是blank


