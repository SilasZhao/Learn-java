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

ssh-keygen -t rsa -C “songyan_zhao@163.com”
	