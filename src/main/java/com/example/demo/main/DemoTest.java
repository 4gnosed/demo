package com.example.demo.main;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

class Food {
}

class Fruit extends Food {
}

class Apple extends Fruit {
}

class Banana extends Fruit {
}

@Transactional
@Slf4j
public class DemoTest extends RecursiveTask<Long> implements Comparator<Integer> {

    private static ForkJoinPool forkJoinPool = new ForkJoinPool();

    private static DemoTest demoTest = new DemoTest();
    private String s;

//    DemoTest(){
//        System.out.println("create....");
//    }

    public static Long getEveryDayTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.add(Calendar.DATE, 1);
        return calendar.getTimeInMillis();
    }

    public static void main(String[] args) throws Exception {
              User user = (User) setterByReflect(User.class);
        System.out.println(JSON.toJSONString(user));
    }

    private static Object setterByReflect(Class<?> aClass){
        Object o1=null;
        try {
            o1 = aClass.getDeclaredConstructor().newInstance();
            Field[] fields = aClass.getDeclaredFields();
            for (Field field : fields) {
                if(field.getType()==String.class){
                    field.set(o1,"-");
                }else if(field.getType()==Long.class){
                    field.set(o1,152L);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return o1;
    }

    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        // ?????? 2 ?????????????????????????????????????????????????????????
        ListNode prev = null, tmp = null;
        prev = head;
        tmp = head.next;

        while (tmp != null) {
//            // ??????????????????????????????????????????
//            tmp = head.next;
//
//            // ?????????????????? next ???????????????????????????
//            head.next = prev;
//
//            // ???????????????????????????????????????????????????????????????
//            prev = head;
//
//            // ???????????????????????????????????????????????????????????????
//            head = tmp;
            tmp.next=prev;
            prev=tmp;
            tmp=tmp.next;

        }
        head.next=null;

        // ????????????????????????
        return prev;
    }

    private static void copyList() {
        ArrayList<User> list = new ArrayList<>();
        User user = User.builder().name("11").user(User.builder().name("22").user(User.builder().name("33").user(User.builder().name("44").build()).build()).build()).build();
        list.add(user);

        List<User> list1 = list.parallelStream().collect(Collectors.toList());

        List<JSONObject> jslist = JSON.parseArray(JSON.toJSONString(list), JSONObject.class);
        user.setName("jfkda");
        for (User u : list) {
            System.out.println(u);
        }
        for (JSONObject js : jslist) {
            System.out.println(js);
        }

        for (User u : list1) {
            System.out.println(u.toString());
        }
    }

    private static void catchException() {
        User user = User.builder().name("namefda").build();
        try {
            int i=0;
            int j=1;
            int z = j / i;
        }catch (Exception e){
            log.info("user??????{}", JSON.toJSONString(user));
            log.error("????????????",e);
        }
    }

//    private static void ihavenoname() {
//        ArrayBlockingQueue<String> abq = new ArrayBlockingQueue<>(5);
//        ArrayList<User> users = new ArrayList<User>();
//        users.add(new User("tom", "bb", "cc", 100L));
//        users.add(new User("tom", "bb", "cc", 5000L));
//        users.add(new User("tom", "dd", "ee", 30L));
//        users.add(new User("jerry", "dd", "ee", 40L));
//        users.add(new User("jerry", "dd", "ee", 200L));
//        users.add(new User("jerry", "dd", "ee", 20L));
//        Map<String, User> userMap = users.stream().sorted(Comparator.comparing(User::getScope)).collect(Collectors.toMap(User::getName, Function.identity(), (b1, b2) -> b2));
//        for (String s : userMap.keySet()) {
//            System.out.println(userMap.get(s));
//        }
//    }
//
//    private static void summaryMultiKey() {
//        ArrayList<User> users = new ArrayList<User>();
//        users.add(new User("tom", "bb", "cc", 100L));
//        users.add(new User("tom", "bb", "cc", 50L));
//        users.add(new User("jerry", "dd", "ee", 30L));
//        users.add(new User("jerry", "dd", "ee", 40L));
//
//        users.stream()
//                .collect(Collectors
//                        .groupingBy(
//                                user -> User.builder().name(user.name).phone(user.phone).address(user.address).build(),
//                                Collectors.summarizingLong(user -> user.scope)
//                        )
//                )
//                .forEach((k, v) -> {
//                    k.scope = v.getSum();
//                    System.out.println(k);
//                });
//    }

    static void getCapacity() throws Exception {

        //??????????????????15???????????????HashMap
        HashMap m = new HashMap(15);
        //??????HashMap?????????
        Class<?> mapType = m.getClass();
        //????????????????????????????????????getDeclaredFields()????????????????????????
        Field threshold = mapType.getDeclaredField("threshold");
        //????????????????????????????????????
        threshold.setAccessible(true);
        //???????????????????????????HashMap?????????????????????????????????capacity????????????????????????
        Method capacity = mapType.getDeclaredMethod("capacity");
        //??????????????????????????????
        capacity.setAccessible(true);
        //?????????????????????HashMap?????????????????????????????????
        System.out.println("?????????" + capacity.invoke(m) + "    ?????????" + threshold.get(m) + "    ???????????????" + m.size());
        for (int i = 0; i < 100; i++) {
            m.put(i, i);
            //????????????HashMap?????????????????????????????????
            System.out.println("?????????" + capacity.invoke(m) + "    ?????????" + threshold.get(m) + "    ???????????????" + m.size());

        }
    }


    private static void testParallel() {
        System.out.println(measureSumPerf(DemoTest::forSum, 50_000_000) + "msec");
        System.out.println(measureSumPerf(DemoTest::iterativeSum, 50_000_000) + "msec");
        System.out.println(measureSumPerf(DemoTest::parallelSum, 50_000_000) + "msec");
        System.out.println(measureSumPerf(DemoTest::parallelRangedSum, 50_000_000) + "msec");
    }

    private static long measureSumPerf(Function<Long, Long> adder, long n) {
        long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            long sum = adder.apply(n);
            long duration = (System.nanoTime() - start) / 1_000_000;
            System.out.println("sum:" + sum);
            fastest = duration < fastest ? duration : fastest;
        }

        return fastest;
    }

    //for??????
    private static long forSum(long n) {
        long sum = 0;
        for (int i = 1; i <= n; i++) {
            sum += i;
        }
        return sum;
    }

    //???????????????
    private static long iterativeSum(long n) {
        return Stream.iterate(1L, i -> i + 1)
                .limit(n)
                .reduce(0L, Long::sum);
    }

    //???????????????
    private static long parallelSum(long n) {
        return Stream.iterate(1L, i -> i + 1)
                .limit(n)
                .parallel()
                .reduce(0L, Long::sum);
    }

    //???????????????????????????long????????????????????????????????????
    //??????????????????????????????????????????????????????????????????????????????
    private static long parallelRangedSum(long n) {
        return LongStream.rangeClosed(1, n)
                .parallel()
                .reduce(0L, Long::sum);
    }


    private static void pythagoreanTriples() {
        final Stream<int[]> stream = IntStream.rangeClosed(1, 50)
                .boxed()
                .flatMap(a -> IntStream.rangeClosed(a, 50)
                        //???????????????????????????sqrt??????????????????????????????????????????????????? .filter(t -> t[2] % 1 == 0)
//                        .filter(b -> Math.sqrt(b * b + a * a) % 1 == 0)
                        //??????.mapToObj??????
//                        .boxed()
//                        .map
                        .mapToObj(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)})
                        .filter(t -> t[2] % 1 == 0));
        stream.forEach(r -> System.out.println("(" + r[0] + "," + r[1] + "," + r[2] + ")"));
    }

    /**
     * flatMap ?????????????????????
     */
    private static void testFlatMap() {
        //        String[] s= {"hello","world"};
//        final String[] split = s[0].split("");
//        Arrays.stream(split).forEach(System.out::println);
//        final List<String> list = Arrays.stream(s).map(o -> o.split("")).flatMap(Arrays::stream).distinct().collect(Collectors.toList());
//        list.stream().forEach(System.out::print);

        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(3, 4);
        final List<int[]> collect = numbers1.stream().
                flatMap(i -> numbers2.stream().filter(j -> (i + j) % 3 == 0).map(j -> new int[]{i, j}))
                .collect(Collectors.toList());

        for (int[] ints : collect) {
            System.out.println("(" + ints[0] + "," + ints[1] + ")");
        }
//        (2,4)
//        (3,3)
    }

    private static void testLambda() throws IOException {
        final DemoTest d = new DemoTest();
        String s = "-----------";
        final String s1 = processFile(br -> br.readLine() + d.s);
        final String s2 = processFile(br -> br.readLine() + s + br.readLine());
        System.out.println("s1:" + s1);
        System.out.println("s2:" + s2);
        //??????????????????????????????lambda?????????????????????????????????(java8?????? P52)
        //        s="fdasuo";//build failure
        //??????????????????????????????lambda????????????????????????????????????
        d.s = "jfdkl;asjfdl;sa";
    }

    public static String processFile(BufferedReaderProcessor brp) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("lambda.txt"))) {
            return brp.process(br);
        }
    }

    private static void testAIO() {
        try {
            writeFileAio("aio_test.txt", "??????????????????asynchronousIo ???????????????");
            readFileAio("aio_test.txt");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public static void writeFileAio(String fileName, String fileContent) throws IOException {
        File file = new File(fileName);
        file.createNewFile();
        try (AsynchronousFileChannel channel = AsynchronousFileChannel.open(Paths.get(fileName), StandardOpenOption.WRITE)) {
            ByteBuffer encode = Charset.forName("utf-8").encode(fileContent);
            Future<Integer> write = channel.write(encode, 0);
            while (!write.isDone()) {
            }
        }
    }

    public static void readFileAio(String fileName) throws IOException, ExecutionException, InterruptedException {
        try (AsynchronousFileChannel channel = AsynchronousFileChannel.open(Paths.get(fileName), StandardOpenOption.READ)) {
            StringBuilder sb = new StringBuilder();
            ByteBuffer buffer = ByteBuffer.allocate(32);
            Charset charset = Charset.forName("utf-8");
            int pos = 0;
            while (pos < channel.size()) {
                Future<Integer> read = channel.read(buffer, pos);
                while (!read.isDone()) {
                }
                pos += read.get();
                buffer.flip();
                sb.append(charset.decode(buffer).toString());
                buffer.compact();
            }
            System.out.println(sb);
        }
    }


    static void readNIO() {
        String pathname = "nio_test.txt";
        try (FileInputStream fin = new FileInputStream(new File(pathname));) {
            FileChannel channel = fin.getChannel();

            int capacity = 50;// ??????
            ByteBuffer bf = ByteBuffer.allocate(capacity);
            System.out.println("????????????" + bf.limit() + "????????????" + bf.capacity()
                    + "????????????" + bf.position());
            int length = -1;

            while ((length = channel.read(bf)) != -1) {

                /*
                 * ????????????????????????????????????0??????limit????????????, ??????????????????????????????????????????0????????????
                 */
                bf.clear();
                byte[] bytes = bf.array();
                System.out.write(bytes, 0, length);
                System.out.println();

                System.out.println("????????????" + bf.limit() + "????????????" + bf.capacity()
                        + "????????????" + bf.position());

            }

            channel.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void writeNIO() {
        String filename = "nio_test.txt";
        try (FileOutputStream fos = new FileOutputStream(new File(filename));) {

            FileChannel channel = fos.getChannel();
            ByteBuffer src = Charset.forName("utf8").encode("??????????????????????????????");
            // ????????????????????????limit???????????????????????????????????????????????????
            System.out.println("??????????????????limit???" + src.capacity() + ","
                    + src.limit());
            int length = 0;

            while ((length = channel.write(src)) != 0) {
                /*
                 * ????????????????????????clear????????????????????????????????????????????? ??????????????????????????????????????????
                 */
                System.out.println("????????????:" + length);
            }
            channel.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static void testSerializableSingleton() {
        //Write Obj to file
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("tempFile"))) {
            oos.writeObject(Singleton.getSingleton());
            //Read Obj from file
            File file = new File("tempFile");
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                Singleton newInstance = newInstance = (Singleton) ois.readObject();
                //??????????????????????????????
                System.out.println(newInstance == Singleton.getSingleton());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void testExternalizable() {
        Dog dog = new Dog();
        dog.setName("anubi");
        dog.setAge(9);
        dog.setRank(1);
        dog.setPrice(783.22);
        System.out.println(dog.toString());

        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream("tmpFile"));
            oos.writeObject(dog);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(oos);
        }

        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(new File("tmpFile")));
            Object o = ois.readObject();
            Dog dog1 = (Dog) o;
            System.out.println(dog1);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void testSerializable() {
        SmallDog smallDog = new SmallDog();
        smallDog.setName("anubi");
        smallDog.setAge(9);
        smallDog.setPrice(783.22);
        SmallDog.s = "hello";
        System.out.println(smallDog.toString());
        System.out.println(SmallDog.s);

        SmallDog smallDogg = new SmallDog();
        smallDogg.setName("kafka");
        smallDogg.setAge(18);
        smallDogg.setPrice(324.22);


        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream("tmpFile"));
            oos.writeObject(smallDog);
            System.out.println(new File("tmpFile").length());
            oos.writeObject(smallDogg);
            System.out.println(new File("tmpFile").length());
            smallDogg.setName("kafkkkkkkkkkkkkkkkkkk");
            oos.writeObject(smallDogg);
            System.out.println(new File("tmpFile").length());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(oos);
        }

        SmallDog.s = "world";

        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(new File("tmpFile")));
            Object o = ois.readObject();
            SmallDog smallDog1 = (SmallDog) o;
            System.out.println("smallDog1:" + smallDog1);
            System.out.println(SmallDog.s);

            Object smallDogg1 = ois.readObject();
            System.out.println("smallDogg1:" + smallDogg1);

            Object smallDogg11 = ois.readObject();
            System.out.println("smallDogg11:" + smallDogg11);

            System.out.println(smallDog1 == smallDogg1);
            System.out.println(smallDogg1 == smallDogg11);


        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void testExtends(List<? extends Fruit> list) {

        //??????,extends??????????????????,????????????,?????????.
        //??????Fruit??????????????????Apple??????Banana,??????????????????????????????????????????Apple??????Banana?????????????????????????????????????????????
        //list.add(new Apple());

        //?????????????????????
        //??????????????????
        Fruit fruit = list.get(1);
        System.out.println(fruit);
    }

    public static void testSuper(List<? super Fruit> list) {

        //super???????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????
        //????????????Fruit?????????????????????Food??????(Object???????????????)
        //????????????Food????????????????????????
        //?????????????????????
        list.add(new Apple());
        list.add(new Fruit());
//        list.add(new Food());

        Fruit fruit = (Fruit) list.get(1);
        System.out.println(fruit);
    }

    private static void testCusAnnotation() throws InvocationTargetException, IllegalAccessException {
        Class<Test> clazz = Test.class;
        if (clazz.isAnnotationPresent(Yello.class)) {
            Yello yello = clazz.getAnnotation(Yello.class);
            System.out.println(yello.name());
            for (Method method : clazz.getDeclaredMethods()) {
                method.setAccessible(true);
                if (method.isAnnotationPresent(White.class)) {
                    White white = method.getAnnotation(White.class);
                    System.out.println(white.name());
                    method.invoke(new Test(), "hahahahha");
                }
            }
        }
    }

    private static void testCglib() {
        Test test = (Test) new CglibProxy().getProxy(Test.class);
        test.justSay("cglibbbbbbbbbbbbbbbbbbbb");
    }

    private static void testJdkAop() {
        TestInterfaceImpl target = new TestInterfaceImpl();
        TestInterface proxy = (TestInterface) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), new CustomHandler(target));
        proxy.sayHello("jjjjjjjjjdka");
    }

    private static void testArrayList() {
        try {
            ArrayList<Object> objects = new ArrayList<>(11);

            List<Object> synchronizedList = Collections.synchronizedList(objects);
            Vector<Object> vector = new Vector<>();

            HashMap<Object, Object> map = new HashMap<>();
            Map<Object, Object> synMap = Collections.synchronizedMap(map);
            ConcurrentHashMap<Object, Object> concurrentHashMap = new ConcurrentHashMap<>();
            Hashtable<Object, Object> hashtable = new Hashtable<>();

            objects.add(1);
            objects.add("hello");
            objects.add(2);

            objects.add(1);
            objects.add("hello");
            objects.add(4);

            Iterator<Object> iterator = objects.iterator();
//            while (iterator.hasNext()){
//                System.out.println(iterator.next());
//                iterator.remove();
//            }

            ListIterator<Object> listIterator = objects.listIterator(3);
            while (listIterator.hasPrevious()) {
                System.out.println(listIterator.previous());
                listIterator.remove();
            }
            System.out.println("objects.size():" + objects.size());

//            System.out.println(objects.get(1)==null);
//            System.out.println(objects.get(1).equals(null));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void printClassLoader() {
        ClassLoader c = DemoTest.class.getClassLoader();  //??????DemoTest??????????????????
        System.out.println(c);
        ClassLoader c1 = c.getParent();  //??????c????????????????????????????????????
        System.out.println(c1);
        ClassLoader c2 = c1.getParent();//??????c1????????????????????????????????????
        System.out.println(c2);
    }


    private static void testBeanCopier() {
        FromBean fromBean = new FromBean();
        fromBean.setName("?????????");
        fromBean.setMoney(898.56);
        fromBean.setIdno("jkehro");
        fromBean.setAge(880);
        fromBean.setAddress("10.10.10.11");
        ToBean toBean = new ToBean();

        BeanCopier beanCopier = BeanCopier.create(FromBean.class, ToBean.class, false);
        beanCopier.copy(fromBean, toBean, null);

        long begin1 = System.nanoTime();
        for (int i = 0; i < 1000000; i++) {
            beanCopier.copy(fromBean, toBean, null);
        }
        long end1 = System.nanoTime();
        System.out.println("beanCopier.copy ??????:" + (end1 - begin1) / 1000000);

        long begin2 = System.nanoTime();
        for (int i = 0; i < 1000000; i++) {
            BeanUtils.copyProperties(fromBean, toBean);
        }
        long end2 = System.nanoTime();
        System.out.println("BeanUtils.copyPropertiess??????:" + (end2 - begin2) / 1000000);

    }

    private void testPattern() {
        //????????????????????????
        Pattern pattern = java.util.regex.Pattern.compile("???????????????");
        Matcher matcher = pattern.matcher("??????????????? Hello World,??????????????? Hello World");
//?????????????????????????????????
        System.out.println(matcher.replaceAll("Java"));
        System.out.println(matcher.replaceFirst("Java"));

        //??????html??????
        Pattern pattern1 = java.util.regex.Pattern.compile("<.+?>", Pattern.DOTALL);
        Matcher matcher1 = pattern1.matcher("<a href=\"index.html\">??????</a>");
        String string = matcher1.replaceAll("");
        System.out.println(string);
    }

    public void test() throws Exception {

        long i = 1L << 6;
        long j = 1L << 2;
        long z1 = i | j;
        long z2 = i & j;
        long z3 = i ^ j;
        System.out.println(Long.toBinaryString(i));
        System.out.println(Long.toBinaryString(j));
        System.out.println(Long.toBinaryString(z1));
        System.out.println(Long.toBinaryString(z2));
        System.out.println(Long.toBinaryString(z3));

        String name = "http://ns1.jieshunpay.cn/h5-cs/mgr-web/index.html#/login";
        System.out.println(URLEncoder.encode(name, "utf-8"));


        DemoTest demoTest = new DemoTest();
        Class clazz = demoTest.getClass();
//        demoTest=clazz.newInstance();
//        clazz.getConstructor ()
//        ProxyUtil.proxy()

        SimpleDateFormat sdf = new SimpleDateFormat();
        String format = sdf.format(new Date());

    }

    @Override
    public int compare(Integer o1, Integer o2) {
        return 0;
    }

    @Override
    protected Long compute() {
        return null;
    }
}
