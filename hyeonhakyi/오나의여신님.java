package ex0401;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 오나의여신님 {
    private static class Person{
        int x;
        int y;
        int count;
        public Person(int x,int y,int count){
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }
    private static class Demon{
        int x;
        int y;
        public Demon(int x,int y){
            this.x = x;
            this.y = y;
        }
    }

    private static int n,m,result;
    private static char[][] arr;
    private static boolean[][] visited;
    private static int[] dx = {-1,1,0,0};
    private static int[] dy = {0,0,-1,1};
    private static Queue<Person> person;
    private static Queue<Demon> demon;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int t = Integer.parseInt(br.readLine());

        for(int tc = 1; tc <= t; tc++){
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            person = new LinkedList<>();
            demon = new LinkedList<>();
            arr = new char[n][m];
            visited = new boolean[n][m];
            result = -1;

            for(int i = 0; i < n; i++){
                String s = br.readLine();
                for(int j = 0; j < m; j++){
                    arr[i][j] = s.charAt(j);

                    if(arr[i][j] == 'S'){
                        person.offer(new Person(i,j,0));
                    }else if(arr[i][j] == '*'){
                        demon.offer(new Demon(i,j));
                    }
                }
            }
            bfs();

            if(result == -1){
                System.out.println("#" + tc + " GAME OVER");
            }else{
                System.out.println("#" + tc + " " + result);
            }
        }//testCase end
    }//main end

    private static void bfs(){
        while(!person.isEmpty()){
            int len = demon.size();
            for(int i = 0; i < len; i++){
                Demon dem = demon.poll();
                int nowX = dem.x;
                int nowY = dem.y;

                for(int d = 0; d < 4; d++){
                    int nx = nowX + dx[d];
                    int ny = nowY + dy[d];

                    if(nx >= 0 && ny >= 0 && nx < n && ny < m && !visited[nx][ny] && arr[nx][ny] != 'X' && arr[nx][ny] != 'D'){
                        visited[nx][ny] = true;
                        arr[nx][ny] = '*';
                        demon.offer(new Demon(nx,ny));
                    }
                }
            }

            len = person.size();
            for(int i = 0; i < len; i++){
                Person per = person.poll();
                int nowX = per.x;
                int nowY = per.y;
                int count = per.count;

                for(int d = 0; d < 4; d++){
                    int nx = nowX + dx[d];
                    int ny = nowY + dy[d];

                    if(nx >= 0 && ny >= 0 && nx < n && ny < m && !visited[nx][ny] && arr[nx][ny] != 'X' && arr[nx][ny] != '*'){
                        if(arr[nx][ny] == 'D'){
                            result = count+1;
                            return;
                        }
                        arr[nx][ny] = 'S';
                        visited[nx][ny] = true;
                        person.offer(new Person(nx,ny,count+1));
                    }
                }
            }
        }
    }//bfs end
}//class end
