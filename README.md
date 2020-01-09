
[官方文档地址](https://github.com/alibaba/ARouter)


### SPI (service provide interface)
[步骤]
1.新建module 'interface'
```
package com.kt.aninterface;
public interface Display {
    String display();
}

```
2.其他模块引入实现该module
3. 引入模块在 java同级目录下新建resources文件夹/MATA-INF.services/接口全路径
```
    文件内录入本模块实现该接口类路径
    for example :com.kt.componentdemo.MainDisplay
```
4. 使用ServiceLoader加载
```
    ServiceLoader<Display> loader = ServiceLoader.load(Display.class);
    mIterator = loader.iterator();
    while (mIterator.hasNext()){
          mIterator.next().display();
    }
```
>懒加载思想
[文章资源](https://www.jianshu.com/p/deeb39ccdc53)
