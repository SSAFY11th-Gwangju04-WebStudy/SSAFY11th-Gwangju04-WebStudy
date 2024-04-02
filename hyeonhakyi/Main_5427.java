package ex0402;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_5427 {
    static class Person{
        int x;
        int y;
        int time;
        public Person(int x,int y,int time){
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }
    static int h,w,result;
    static char[][] arr;
    static Queue<Person> per;
    static Queue<Person> fir;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    public static void main(String[] args) throws NumberFormatException,IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());

        for(int tc = 0; tc < t; tc++){
            st = new StringTokenizer(br.readLine()," ");
            h = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());

            arr = new char[w][h];

            per = new LinkedList<>();
            fir = new LinkedList<>();

            for(int i = 0; i < w; i++){
                String s = br.readLine();
                for(int j = 0; j < h; j++){
                    arr[i][j] = s.charAt(j);

                    if(arr[i][j] == '@'){
                        per.offer(new Person(i,j,0));
                    }else if(arr[i][j] == '*'){
                        fir.offer(new Person(i,j,0));
                    }
                }
            }

            result = 0;
            bfs();

            if(result == 0){
                sb.append("IMPOSSIBLE\n");
            }else{
                sb.append(result).append("\n");
            }
        }//testCase end
        System.out.println(sb.toString());
    }//main end

    public static void bfs(){
        while (!per.isEmpty()) {
            fire();

            int len = per.size();
            for (int i = 0; i < len; i++) {
                Person now = per.poll();

                for (int d = 0; d < 4; d++) {
                    int nx = now.x + dx[d];
                    int ny = now.y + dy[d];

                    if (!check(nx, ny)) {
                        result = now.time+1;
                        return;
                    }
                    if (arr[nx][ny] == '.') {
                        per.offer(new Person(nx, ny,now.time+1));
                        arr[nx][ny] = '@';
                    }
                }
            }
        }
    }//bfs end

    public static void fire(){
        int len = fir.size();
        for(int i = 0; i < len; i++){
            Person now = fir.poll();

            for(int d = 0; d < 4; d++){
                int nx = now.x + dx[d];
                int ny = now.y + dy[d];

                if(check(nx,ny) && (arr[nx][ny] == '.' || arr[nx][ny] == '@')){
                    arr[nx][ny] = '*';
                    fir.offer(new Person(nx,ny,0));
                }
            }
        }
    }//fire end

    public static boolean check(int x,int y){
        return x >= 0 && y >= 0 && x < w && y < h ;
    }
}//class end
