[TOC]

## 剑指Offer

### 从1到n整数中1出现的次数

注意是求1出现的次数，而不是求包含1的数字的个数。

1. 一个数abcde，如果e>=1，个位出现次数为abcd+1，如果e<1,个位出现1的个数为abcd；
2. 十位出现1的个数：如果d>1,为abc*10+10;如果d==1，为abc*10+e+1;如果<1，则为abc*10;
3. 同理可以求百位

### 连续子数组的最大和

1. 从前往后相加为currentSum，如果发现累加和为负数，丢掉之前的累加和；
2. greatestSum保存最大的值。

### 最小的K个数

1. 将数组前k个数据赋值到数组heap中，调整heap为大顶堆；

```java
for (int i=k/2-1;i>=0;i--){
        heapAdjust(heap,i);
}
```



2. 比较数据和堆顶heap[0]大小，数据较小则赋值给heap[0]，调整heap为大顶堆。

```java
private static void heapAdjust(int[] array,int nodeToAdjust){
           int temp=array[nodeToAdjust];
           for (int i=2*nodeToAdjust+1;i<array.length;i=2*i+1){
               if (i<(array.length-1)&&array[i]<array[i+1])
                   i++;
               if (temp>array[i])
                   break;
               array[nodeToAdjust]=array[i];
               nodeToAdjust=i;
           }
           array[nodeToAdjust]=temp;

```

### 字符串的排列

1. 首先求所有可能出现在第一个位置的字符，即把第一个字符和后面所有的字符交换；然后固定一个字符，求后面所有字符的排列；
2. 可能出现在第一个位置的字符为stringBuilder.charAt(index)，index为其中任意一个字符；在固定了在index位置的一个字符后，求后面所有字符的排列，递归调用permutation(stringBuilder,index+1)

3. 终止条件：当最终递归调用到index+1=length时，输出此时的stringBuilder，且由于for循环的条件，故不在进行递归调用。

### 二叉搜索树与双向链表

采用中序遍历不会影响还未遍历到的结构

1. 对于二叉搜索树，采用中序遍历按照从小到大的顺序遍历二叉树的每一个结点，因此，只需要记录上一个结点lastNodeInList，将其和当前遍历的结点连接起来，便可实现转换。
2. 初始化：需要一个共享的静态变量lastNodeInList，以便在两次递归调用中传递上一次的结果。初始值为null，因为head的上一个结点为null；
3. 终止条件：考虑到终止时，lastNodeInList指向倒数第二个结点，current指向倒数第一个结点，此时整个双向链表已经完成，此时可以退出递归了；
4. 循环过程：将lastNodeInList的下一节点为current，current的上一节点为lastNodeInList，更新lastNodeInList为current
   特殊情况：

5. 在初始情况下，lastNodeInList为null，此时无法将它下一结点置为current；

```java
    public static BiTreeNode lastNodeInList=null;

    public static BiTreeNode convert(BiTreeNode root){
        convertNode(root);
        BiTreeNode head=lastNodeInList;
        while (head!=null&&head.leftChild!=null){
            head=head.leftChild;
        }
        return head;
    }

    public static void convertNode(BiTreeNode node){
        if (node==null)
            return;
        BiTreeNode current=node;
        //对于双向链表的头结点，current.leftChild为null
        if (current.leftChild!=null)
            convertNode(current.leftChild);
        current.leftChild=lastNodeInList;
        //初始情况下lastNodeInList为null，要和一般情况分开
        if (lastNodeInList!=null)
            lastNodeInList.rightChild=current;
        lastNodeInList=current;
        //对于双向链表的尾结点的情况，此时倒数第一和倒数第二结点都已经连接了，整个双向链表已经形成
        if (current.rightChild!=null)
            convertNode(current.rightChild);
	}
```

### 复杂链表的复制

1. 根据原始链表的每个结点N创建对应的N’，并把N’连接在N后面
2. 设置复制出来的结点的sibling
3. 分成两个链表，复制完成

### 二叉树中和为某一值的路径

1. 前序遍历二叉树，并用一个栈stack来存储路径，用一个变量sumPath存储路径之和；每当前序遍历从子结点返回父节点时，弹出该子结点，并且sumPath减去该结点的值；
2. findPath(BiTreeNode root,int expectedSum)创建变量sumPath和结点栈stack；
3. findPath(BiTreeNode current,int expectedSum,Stack stack,int sumPath)为主要的递归过程；首先将current的值加入sumPath，并压入stack，当为叶节点时判断当前路径sumPath是否等于expectedSum；如果不是叶结点，当current结点的左子结点不为null，递归调用，右子结点同理，注意java函数为复制一个引用传递变量值，所以不同递归深度的sumPath值不同，不用刻意在弹出某个结点时，sumPath减去该值；

4. 从子结点返回父节点的过程，需要弹出子结点。

### 从上往下打印二叉树

1. 要求同一层的结点从左向右顺序打印，可以想到队列；
2. 对于根节点，压入队列的顺序是先左结点，后右结点；考虑第二层，左子树的左结点、右结点进入队列，此时队列第一位为右子树根节点，故将右子树的左结点和右结点压入队列，可以看到第三层是有序的
3. 按照数学归纳法，假设第k层是有序的，队列中存储着有序的第k层的各个结点，递推到第k+1层时，根据队列先入先出的特性，第k+1层也是有序的。
   注意边界条件：仅当结点不为null时将结点压入队列；输入结点为null。

### 栈的压入、弹出序列

思路：

1. 注意压入栈的元素可以随时弹出，因此弹出序列有多种；
2. 创建一个辅助栈，按照压入序列向栈中压入元素；
3. 每当压入一个元素时，如果这个元素刚好是弹出序列元素，那么直接弹出；如果不是，则把压栈序列中还没有入栈的数字压入辅助栈，直到栈顶元素为相应弹出序列元素；如果所有元素都压入栈而和按照弹出顺序的元素都不相等，说明该弹出序列不是该栈的弹出顺序。

具体步骤：

1. 初始条件：先往辅助栈stack中压入一个元素，初始化pushIndex为1，popIndex为0；
2. 终止条件：当array2是array1的出栈顺序时，正常经过循环退出，此时array1和array2都遍历了一遍，pushIndex=popIndex=array.length；当array2不是array1的出栈顺序，此时array1的所有元素都压入stack，均不等于array[popIndex]；
3. 循环过程：比较栈顶元素和array2[popIndex]，不相等则继续往里面压入元素，直到栈顶元素和array2[popIndex]相等或者已经将所有的元素压入stack，检查如果此时压入所有元素仍然不相等，返回false；正常情况下，此时栈顶元素和array2[popIndex]相等，将其弹出，并popIndex++，进行下一组循环。当整个循环正常结束，即pushIndex=popIndex=array.length时，返回true。
4. 边界条件：输入的数组为null或者元素为0，或者两数组唱的不相等。

### 包含min函数的栈

1. 错误的思路：设置一个成员变量，如果添加的元素变量比它小，则更新该成员变量。这种思路当从栈中弹出了最小元素，如何保证该成员变量是最小的；
2. 正确的做法是将每次的最小元素都保存在另外一个辅助栈中；
3. 注意边界条件：当栈为空时返回栈的最小值。

### 顺时针打印矩阵

1. 采用一个二维数组来输入矩阵元素；
2. 顺时针打印一个二维数组，将二维数组分成多个圈，起点为(start,start)，从外圈向内圈循环遍历，循环继续的条件是start小于行和列的二分之一 ；

3. 由于最后一圈可能退化成只有一行或者一列，将打印一圈分成四个过程，如下图所示：过程1是必须的；过程2的前提条件是终止行号大于起始行号；
   过程3的前提条件是起始列号小于终止列号；过程4的前提条件是终止行号大于起始行号加1，且起始列号小于终止列号；

![å¨è¿éæå¥å¾çæè¿°](https://img-blog.csdnimg.cn/20190206172645358.png)

```java
public static void printCircleClockwisely(int[][] array,int start,int row,int column){
    int endRow=row-start-1;
    int endColumn=column-start-1;
    //过程1，从(start,start)到(start,endColumn)
    for (int i=start;i<=endColumn;i++){
        System.out.println(array[start][i]);
    }
    //过程2，从(start+1,endColumn)到(endRow,endColumn)
    if (endRow>start){
        for (int i=start+1;i<=endRow;i++)
            System.out.println(array[i][endColumn]);
    }
    //过程3，从(endRow,endColumn-1)到(endRow,start)
    if (start<endColumn){
        for (int i=endColumn-1;i>=start;i--)
            System.out.println(array[endRow][i]);
    }
    //过程4，从(endRow-1,start)到(start+1,start)，完成一圈循环
    if (endRow>(start+1)&&start<endColumn)
        for (int i=endRow-1;i>start;i--)
            System.out.println(array[i][start]);
}
```
### 二叉树的镜像

1. 首先考虑对应单一一个结点，交换其左右结点需要一个引用temp来暂存一个结点，以便交换。此时要注意三种情况：
   * 两个结点都不为null；
   * 有一个结点为null；
   * 两个结点都为null。其实此处可以不管，虽说第三种不需要交换，但是需要增加对每个结点判断其左右结点是否为null，这时可以直接把这种情况也直接交换，以减少判断。
2. 注意鲁棒性，mirror()函数输入为null时，直接退出；在主函数mirrorRecursively()中，当root不为null时，首先交换root结点，然后递归调用它的左右子结点。

### 树的子结构

1. 首先在A树中找到树B的根节点node，再比较两个树node和B是否相等；
2. 比较node是否包含B的树结构：
   * 采用递归的方式，遍历时只要有一个结点值不相等，就返回false；
   * 当B的子结点到达尾部null时，返回true；
   * 如果B的子节点不为null，node的子结点为null时，要返回false，这个检查要在比较树结点值前进行，否则会NullPointerException报错。

```java
public static boolean hasSubtree(BiTreeNode root1, BiTreeNode root2){
    boolean result =false;
    if (root1!=null&&root2!=null){
        if (root1.data==root2.data)
            result=doesTree1HaveTree2(root1,root2);
        if (!result)
            result=hasSubtree(root1.leftChild,root2);
        if (!result)
            result=hasSubtree(root1.rightChild,root2);
    }
    return result;
}

public static boolean doesTree1HaveTree2(BiTreeNode root1,BiTreeNode root2){
    if (root2==null)
        return true;
    if (root1==null)
        return false;
    if (root1.data!=root2.data){
        return false;
    }

    return doesTree1HaveTree2(root1.leftChild,root2.leftChild)&&doesTree1HaveTree2(root1.rightChild,root2.rightChild);
}
```
### 合并两个排序的链表

非递归方式

1. 需要一个存储合并链表的头结点mergeHead和一个存储合并链表的结点mergeCurrent；
2. 初始化：判断两个链表head1和head2哪个小，小的作为mergeHead，同时将小的那一方head指向head.next；mergeHead指向前一个加入链表的元素，即mergeHead；
3. 循环过程：比较head1和head2数字大小，将mergeCurrent.next指向小的结点，并更新mergeCurrent=mergeCurrent.next，将较小的结点存在的链表head=head.next。
4. 终止条件：当有一个链表头结点为null时，可以退出了。此时，注意mergeCurrent为一个链表的尾结点，需要将它和另一个尚未遍历完的链表连起来，即mergeCurrent.next=head(还未遍历完的结点)。
5. 注意边界条件：输入head为null时的情况。输入head只有一个元素，不需要另外注意，也满足的。

递归方式

1. 比较两个头结点大小，小的为mergeCurrent，对于小的那个结点存在的链表head=head.next，对新的head1和head2调用merge函数，并将mergeCurrent.next指向它；
2. 终止条件:当一个head==null时；此时将另一个链表头结点返回。

### 反转链表(每n个反转一下)

1. 类似于数学归纳法：第i个结点以前的i-1个结点已经满足条件了，i-1这个节点用preNode来指代，i结点用currentNode指代，此时如果将currentNode.next直接指向preNode，则后面的链表就断了，因此要用一个结点nextNode来存储结点以防止链表断了。
2. 初始化：currentNode就是头结点，preNode=null，因为currentNode.next会指向preNode，而此时的头结点应该是新链表的尾结点，nextNode=null，暂时不给nextNode指向对象。
3. 终止条件:当currentNode为尾结点时，仍然需要循环一次，以使得尾结点指向preNode，并成为新链表的头结点，因此，终止条件为currentNode==null。
4. 循环过程：首先存储currentNode的下一结点以保证链表不断，其次将currentNode指向前一结点preNode，再将preNode和currentNode更新，
5. 注意边界条件：头指针为null的特殊情况

```java
public static ListNode ReverseList(ListNode head){
        if(head==null)
            return null;
        ListNode reversedHead=null;
        ListNode currentNode=head;
        ListNode preNode=null;
        while (currentNode!=null){
//          保存下一结点，当当前结点指向前一结点时，链表断裂，保存了后一结点才可以续上。
            ListNode next=currentNode.next;
//          将当前结点指向前一结点
            currentNode.next=preNode;
//          更新前一节点为原链表的当前结点
            preNode=currentNode;
//          更新当前结点为原链表的下一结点
            currentNode=next;
        }
        return preNode;
    }
```

变式：每n个反转一下

1. 将原链表拆分成长度为n的链表
2. 每个链表都反转一下
3. 反转之后拼接起来

```java
/**
 * 每n个反转一下链表
 * @param head
 * @param n
 * @return
 */
public static ListNode reverseList(ListNode head,int n){
    List<ListNode> oldHeads=new ArrayList<>();
    int count=0;
    while (head!=null){
        if (count==n-1){
            oldHeads.add(head);
            //断开链表
            ListNode next=head.next;
            head.next=null;
            head=next;
            count=0;
        }
        else {
            head=head.next;
            count++;
        }
    }
    //每个链表都反转
    List<ListNode> newHeads=new ArrayList<>();
    for (ListNode node:oldHeads){
        newHeads.add(reverseList(node));
    }
    //反转之后连接起来
    for (int i=0;i<newHeads.size()-1;i++){
        oldHeads.get(i).next=newHeads.get(i+1);
    }
    return newHeads.get(0);
}
```

### 链表中倒数第k个结点

1. 两个指针a和b，a指向第一个，b先指向第k个；
2. ab同时移动，直到b指向链表的尾部;
3. 注意边界条件：输入链表为null，K<=0,K大于链表的长度。

### 调整数组顺序使奇数位于偶数前面

1. 两个指针a和b，a指向第一个数字，b指向最后一个数字；
2. a向后移动直到指向一个偶数，b向前移动直到指向一个奇数，交换两数；
3. 直到a和b指向同一个数。

### 在O(1)时间删除链表结点

1. 尾结点，删除结点位于尾部时，需要遍历得到该结点的前一结点。
2. 非尾结点，把后一节点的内容复制到该结点，该结点指向下下结点；

### 打印1到最大的n位数

考虑大数问题；

1. 在字符串上模拟数字加法的解法；
2. 终止条件是第一个字符产生了进位，以此快速判断是否结束；
3. 输出时注意去掉补位用的0；
4. 或者把问题转换成数字排列问题，采用递归。数字的每一位都可能是0-9中的一位数，然后设置下一位。

```java
    /**
     * 更新字符数组，并判断是否终止
     * @param digits 字符数组
     * @return
     */
    public static boolean increment(char[] digits){
        boolean isOverFlow=false;
        int nTakeOver=0;
        for (int i=digits.length-1;i>=0;i--){
            int nSum=digits[i]-'0'+nTakeOver;
            if (i==digits.length-1)
                nSum++;
//          进位发生了
            if (nSum>=10){
//              第一位进位了
                if (i==0){
                    isOverFlow=true;
                }
                nTakeOver=1;
                digits[i]='0';
            }
//            不进位时直接退出循环了，所以不需要将nTakeOver置0
            else {
                digits[i]=(char)(digits[i]+1);
                break;
            }
        }
        return isOverFlow;
    }

```

### 数值的整数次方

1. 采用递归减少乘法次数
2. 注意两种情况：
   * 底数可能是0；
   * 指数可能是负数；

```java
/**
     * 处理输入为正指数的情况，采用迭代的方法，相比于一次一次乘，可以更快的得到
     * @param base
     * @param exponent
     * @return
     */
    private static double powerWithAbsExponent(double base, int exponent){
        if (exponent==0){
            return 1;
        }
        if (exponent==1){
            return base;
        }
        double result=powerWithAbsExponent(base,exponent>>>1);
        result*=result;
        if ((exponent&1)==1)
            result*=base;
        return result;
    }
```

### 二进制中1的个数

移位代替

```java
public static int number(int num){
    int count=0;
    
    for(int i=0;i<32;i++){
        if ((num&1)==1){
            count++;
        }
        num=num>>>1;
    }
    return count;

}
```
### 斐波那契数列

```java
public int Fibonacci(int n) {
        int[] result01={0,1};
        if(n<2)
            return result01[n];
        int f0=0;
        int f1=1;
        int result=0;
        for(int i=0;i<n-1;i++){
            result=f1+f0;
            f0=f1;
            f1=result;
            
        }
        return f1;
    }
```

### 旋转数组的最小数字

前面的子数组的元素都大于或者等于后面子数组的元素，最小的元素刚还是这两个子数组的分界线。可以利用二分查找法来寻找最小的元素。

找到了最大数实际上也是找到了最小数

```java
public int minNumberInRotateArray(int [] a) {
        int start=0;
    int end=a.length-1;
    int mid=0;
    //a[start]会增加然后到达最高点
    while (a[start]>=a[end]){
        mid=(start+end)/2;
        if (a[start]<=a[mid]){
            if (start==mid) {
                break;
            }
            start=mid;
        }else {
            end=mid;
        }
    }
    return a[end];
}
```
### 重建二叉树

递归方法

```java
    public TreeNode reConstructBinaryTree(int [] pre,int [] in) {
        if (pre == null || in == null) {
            return null;
        }
        if (pre.length == 0 || in.length == 0) {
            return null;
        }
        if (pre.length != in.length) {
            return null;
        }
        TreeNode root = new TreeNode(pre[0]);
        for (int i = 0; i < pre.length; i++) {
            if (pre[0] == in[i]) {
                root.left = reConstructBinaryTree(
                                Arrays.copyOfRange(pre,1,i+1),Arrays.copyOfRange(in,0,i));
                root.right = reConstructBinaryTree(
                Arrays.copyOfRange(pre,i+1,pre.length),Arrays.copyOfRange(in,i+1,in.length));
            }
        }
        return root;
    }
```

### 替换空格

从后往前替换，而不是从前往后替换，避免多次挪动后面的字符串。

### 二维数组中的查找

选取(0,array[0].length-1)或者(array.length-1,0)，每次剔除一行或者一列

```java
public boolean findInArray(int[][] array, int target){
	if (array == null)
		return false;
	int row = array.length-1;
	int column=0;
	while (row>=0 && column <array[0].length){
		if (array[row][column]==target)
			return true;
		if (tgrget > array[row][column])
			column++;
		else 
			row--;
	}
}
```

### 单例模式

* 懒汉模式（线程安全）

```
public class Singleton {  
      private static Singleton instance;  
      private Singleton (){
      }
      public static synchronized Singleton getInstance() {  
      if (instance == null) {  
          instance = new Singleton();  
      }  
      return instance;  
      }  
 }  [![复制代码](https://common.cnblogs.com/images/copycode.gif)](javascript:void(0);)
```

这种写法能够在多线程中很好的工作，但是每次调用getInstance方法时都需要进行同步，造成不必要的同步开销，而且大部分时候我们是用不到同步的，所以不建议用这种模式。

* 双重检查模式 （DCL）

```java
public class Singleton {  
      private volatile static Singleton singleton;  
      private Singleton (){
      }   
      public static Singleton getInstance() {  
      if (instance== null) {  
          synchronized (Singleton.class) {  
          if (instance== null) {  
              instance= new Singleton();  
          }  
         }  
     }  
     return singleton;  
     }  
 }  
```

这种写法在getSingleton方法中对singleton进行了两次判空，第一次是为了不必要的同步，第二次是在singleton等于null的情况下才创建实例。

实例化对象的那行代码（标记为error的那行），实际上可以分解成以下三个步骤：

1. 分配内存空间
2. 初始化对象
3. 将对象指向刚分配的内存空间

但是有些编译器为了性能的原因，可能会将第二步和第三步进行**重排序**,因此多线程情况下可能会得到一个未初始化的对象引用发生错误。volatile可以防止指令重排序，

 

*  静态内部类单例模式

```java
public class Singleton { 
    private Singleton(){
    }
      public static Singleton getInstance(){  
        return SingletonHolder.sInstance;  
    }  
    private static class SingletonHolder {  
        private static final Singleton sInstance = new Singleton();  
    }  
} 
```

第一次加载Singleton类时并不会初始化sInstance，只有第一次调用getInstance方法时虚拟机加载SingletonHolder 并初始化sInstance ，这样不仅能确保线程安全也能保证Singleton类的唯一性，所以推荐使用静态内部类单例模式。

*  枚举单例

```java
public enum Singleton {  
     INSTANCE;  
     public void doSomeThing() {  
     }  
 }  
```

默认枚举实例的创建是线程安全的，并且在任何情况下都是单例