package apriceshittest;

/**
 * 1954
 *
 * @author gzq44
 * @date 2023/12/24 23:30
 **/
public class P1954 {
    public static void main(String[] args) {
        new P1954().minimumPerimeter(10000);
    }
    public long minimumPerimeter(long neededApples) {
        long i = 1;
        long cnt = 8;
        while (true) {
            if (neededApples < cnt) {
                break;
            }
            i++;
            long tmp = i * 8 + ((i - 1) * 2 + 1) *  4 + cnt;
            cnt += tmp;

        }
        return i * 8;
    }
}
