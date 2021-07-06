## Queue接口的API

|             | 抛异常    | 返回特定值 |
|:------------|:----------|:----------|
| add方法     | add(e)    | offer(e)  |
| 删除方法     | remove()  | poll(e)   |
| 获取元素方法 | element() | peek()    |

总结一下，就是add/remove/element 如果在遇到操作失败时，会抛异常；
offer/poll/peek 如果在遇到操作失败时，会返回特殊值。

## Deque接口的API

|      | first         | first         | rear         | rear         |
|:-----|:--------------|:--------------|:-------------|:-------------|
| 插入 | addFirst(e)   | offerFirst(e) | addLast(e)   | offerLast(e) |
| 删除 | removeFirst() | pollFirst()   | removeLast() | pollLast()   |
| 检查 | getFirst()    | peekFirst()   | getLast()    | peekLast()   |

所有addXXX/removeXXX/getXXX 操作失败的时候，都会抛出异常。
offerXXX/pollXXX/peekXXX 操作失败的时候，会返回特殊值。


## 延时队列
传入的延时队列的数据需要实现Delayed接口。
该接口需要标识当前还剩多长时间,以及实现一个compareTo方法。

