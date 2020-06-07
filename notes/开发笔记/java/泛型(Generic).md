# 泛型的作用
- 提供编译期的类型安全 : 提供编译期类型检查,类型不匹配时,编译器会直接报错
- 自动类型转换 : 免去强换操作
- 编写更加通用的代码 : 逻辑完全一样只是参数不同的代码不需要写多份了

# 使用泛型的限制
- 泛型只能使用引用数据类型,无法使用基本数据类型
    ```
    List<Integer> strings = new ArrayList<>();  //编译通过  
    List<int> strings = new ArrayList<>();      //编译异常,泛型类型指定为基本数据类型
    ```
- 无法通过new关键字对泛型实例化,但是可以通过反射拿到泛型类型进行实例化
    ```
    <E> void Func(Class<E> clazz){
        E e = new E();              //编译异常,泛型不能new  
        E e = clz.newInstance();    //编译通过        
    } 
    ```

- 无法使用泛型数组,泛型类数组定义时泛型因为擦除不能指定具体类型,应写通配符?
    ```
    class A< T >{
        T[] = new T[1];                 //编译异常,泛型数组
        A<String>[] = new A<String>[1]; //编译异常,泛型类数组指定了泛型类型
        A<?>[] = new A<?>[1];           //编译通过             
    } 
    ```
- 使用 instanceof 运算符进行运行时类型检查,泛型类中的泛型因为擦除不能指定具体类型,应写通配符?
    ```
    List<String> strings = new ArrayList<>();
    strings instanceof ArrayList<?>()       //编译通过
    strings instanceof ArrayList<String>()  //编译异常,泛型类指定了泛型类型
    ```
- 泛型如果在是实例方法/类中定义,就不能在静态方法/类/属性中使用.因为泛型是在对象实例化时才能确定类型,而静态成员的使用不需要对象实例化
    ```
    class A< T >{
       static T t ;     //编译异常,静态成员生命周期早于实例
    }
    ```
- 泛型类无法直接或间接继承Throwable类,Throwable类型的泛型不能被捕获,但是可以抛出
    ```
    class A<T> extends Exception{}  //编译异常,泛型类继承Throwable   
    ```
    ```
    <T extends Exception> void a() {
        try {

        } catch (T t) {          //编译异常,捕获泛型
            
        }
    } 
    
    ...
    
    <T extends Exception> void b() throws T {} //编译正常        
    ```

# 限定符
- 多限定 : 可以有多个接口,但只能有一个类,类要放在第一个,如下:只有X可以是类,Y、Z只能是接口
    ```
    class A< T extends X & Y & Z> 
    ```
# 通配符
- 不受限通配符 : <?> . 如果不指定通配符边界,则不能依赖任何类型参数进行存取操作
  ```
    void Fun( List<?> list ){
        list.add("sss");        // 编译异常, ? 不能依赖String类型 
        list.add(1);            // 编译异常, ? 不能依赖Integer类型
        list.add(new Object()); // 编译异常, ? 不能依赖Object类型     
        list.add(null);         // 编译通过, ? 没有依赖任何类型
        Object o = list.get(0); // 编译通过, 只能取出Object类型  
        list.contains(1);       // 编译通过
        list.contains("sss");   // 编译通过    
    }         
  ```
- 限定通配符PECS原则 : Producer extends Consumer super.
  - 如果需要从集合中获取类型T,使用上边界通配符
  - 如果需要往集合中存入类型T,使用下边界通配符

  |        |      java      | kotlin | 能执行的操作类型 |     角色类型     |
  |:------:|:--------------:|:------:|:--------------:|:----------------:|
  | 上边界 | < ? extends T> |  out   |  取数据、读操作  | 生产者(Producer) |
  | 下边界 |  < ? super T>  |   in   |  存数据、写操作  | 消费者(Consumer) |


# 泛型擦除
- 泛型是jdk1.5开始引入的特征,虚拟机其实是不支持泛型,为了向下兼容编译期会擦除泛型,所以说Java是一种伪泛型机制
- 通过查看字节码文件可以知道
  - 没限定的情况下,泛型被擦除成Object,使用泛型时会强转为泛型类型
  - 有限定符的情况下,泛型会被擦除成第一个限定类型.在使用其他限定类型时进行强转
- 泛型虽然会被擦除,但类中还是会保存泛型信息,我们可以通过反射拿到泛型类型
    ```
        getGenericType()
    ```
  - TypeVariable : 泛型类型变量,可以泛型上下限等信息
  - ParameterizedType : 具体的泛型类型,可以获得元数据中泛型签名类型(泛型真实类型)
  - GenericArrayType : 当需要描述的类型是泛型类的数组时,比如List[],Map[],此接口会作为Type的实现
  - WildcardType : 通配符泛型,获得上下限信息
