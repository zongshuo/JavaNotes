set集合不允许有重复值，相等的对象不能再次插入，不会覆盖已插入的对象。
set集合使用对象的equals和hashCode方法判断两个对象是否相等。
    首现判断hashCode方法返回值，一致则判断equals方法的返回值，如果依然相等则认定两个对象相等。
    所以使用set集合时需要注意可变对象，因为对象的改变可能导致两个对象由不等变为相等。

//TODO 详细原理？
HashMap -> HashSet->key：封装了map，使用map的key完成去重功能。
LinkedHashSet->LinkedHashMap->key->链表：

TreeSet：自然排序和定制排序，最好放同一个类的对象。
        判断不能的条件为：equals方法返回false或者compareTo结果不为0。