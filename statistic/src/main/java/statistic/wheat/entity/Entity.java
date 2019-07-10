package statistic.wheat.entity;
import java.lang.reflect.Field;
import java.util.*;
import java.util.Map.Entry;
public class Entity<T> implements Comparable<T>{
   @Override
    public int compareTo(T t) {
      return 0;
    }

   public List<T> sortList(List<T> list){
      Collections.sort(list,new Comparator<T>(){
         @SuppressWarnings("unchecked")
         @Override
         public int compare(T t1,T t2) {
            return ((Comparable<T>) t1).compareTo(t2);
         }
      });
      return list;
   }

   /**
    * @function name searchList
    * @Description 多条件查询list
    * @param list
    * @param params
    * @return
    * @date 2018-09-03
    * @author yzh
    */ 
   public List<T> searchList(List<T> list,Map<String,Object> params){
      List<T> newList = new ArrayList<T>(); 
       for(T t:list){
          //判断是否一致的标识
          boolean flag = false;
           Iterator<Entry<String, Object>> it = params.entrySet().iterator();
          //遍历map
           while(it.hasNext()){
             String key = it.next().getKey();
             String value = params.get(key)+"";
             if(value.equals(getFiledValue(key,t))){
                flag = true;
             //如果不一致，终止此次循环，确保所有查询条件一致才一致
             }else{
                flag = false;
                break;
             }
          }
           //如果符合查询条件，那么就加入list中
          if(flag){
             if(!newList.contains(t)){
                newList.add(t);
             }
          }
       }
       return newList;
   }

   /**
    * @function name removeOtherListCaseByTheListCaseName
    * @Description 根据list的某个属性名称删除另一个list的某个元素
    * @param
    * @return
    * @date 2018-08-07
    * @author yzh
    * @param <T>
   **/
   public static <T> List<T> removeOtherListCaseByTheListCaseName(List<T> list1,List<T> list2,String fileName) {
      int []arr = new int[list2.size()]; 
      int [] newArr = new int[arr.length];
      //记录重复元素下标
      int b = 0;
      for(int i = 0;i<list1.size();i++){
         for(int m = 0;m<list2.size();m++){
            T t1 = list1.get(i);
            String filedVal1 = getFiledValue(fileName,t1);
            T t2 = list2.get(m);
            //根据fileName属性获取t1的fileName属性值
            String filedVal2 = getFiledValue(fileName,t2);
            try {
               if(filedVal1.equals(filedVal2)){
                  //做删除操作时，遍历的长度一定要是list.size(),因为后面移除对象之后就会导致list长度动态变化，如果使用常量表示，会导致边界异常
                  //记录重复元素的位置
                  arr[b] = i;
                  b++;
               }else{
                  continue;
               }
            } catch (Exception e) {
               e.printStackTrace();
            } 
         }
      }
      //因为要删除list里面的多个的值，所以会涉及到一个问题，就是当你删除掉下标为1的元素时，
      //原来下标为2的元素会自动改变自己的下标为0，后面的元素依次把自己的下标值减1
      //鉴于这种情况，我们想循环删除list中的多个元素的话，就必须从后往前删，这样保障了你删除了一个元素之后，
      //list中元素的下标移动不会影响到那些需要删除，但是还未删除到的元素 
      //1.顺序排序
      Arrays.sort(arr);
      //2.反向数组
      for(int i = 0;i<arr.length;i++){
         newArr[i] = arr[arr.length-i-1];
      }
      //3.删除元素
      for(int i = 0;i<newArr.length;i++){
         list1.remove(newArr[i]);
      }
      return list1;
   }


   /**
    * @function name getFiledValue
    * @Description 获取List对象元素的某个属性的值
    * @param filedName 字段名
    * @param <T>
    * @return
    * @date 2018-08-07
    * @author yzh
    */ 
   public static <T> String getFiledValue(String filedName,T t){
      Field field = null;
      Object fieldVal = null;
      try {
         field = t.getClass().getDeclaredField(filedName);
         //设置私有属性允许访问，否则无法将值set进实体类的私有属性中
         field.setAccessible(true);
         fieldVal = field.get(t);
      } catch (Exception e) {
         e.printStackTrace();
      }
      return fieldVal+"";
   }
   /**
    * @function name removeListCaseByFieldName
    * @Description 根据实体类的某个字段和输入值来删除元素

    * @param removeByValue 删除值

    * @return
    * @date 2018-09-03
    * @author yzh
    */ 
   public  List<T> removeListCaseByFieldName(List<T> list,String field,String removeByValue){
      for(int i = 0;i<list.size();i++){
         T t = list.get(i);
         if(getFiledValue(field,t).equals(removeByValue)){
            list.remove(t);
         }
      }
      return list;
   }
}