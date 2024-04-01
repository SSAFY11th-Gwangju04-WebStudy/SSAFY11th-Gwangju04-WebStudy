package ex0330;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_17142 {
    private static class Virus{
        int x;
        int y;
        int time;
        public Virus(int x,int y,int time){
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }
    private static int[] dx = {-1,1,0,0};
    private static int[] dy = {0,0,-1,1};
    private static int[][] arr;
    private static int n,m,totalZero,result=Integer.MAX_VALUE;
    private static List<Virus> list = new ArrayList<>();
    private static Virus[] vir;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n][n];
        vir = new Virus[m];
        totalZero = 0;

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());

                if(arr[i][j] == 2){
                    list.add(new Virus(i,j,0));
                }else if(arr[i][j] == 0){
                    totalZero++;
                }
            }
        }

        combi(0,0);
        if(totalZero == 0){
            System.out.println(0);
        }else if(result == Integer.MAX_VALUE){
            System.out.println(-1);
        }else {
            System.out.println(result);
        }
    }//main end

    private static void combi(int count,int idx){
        if(count == m){
            spread(totalZero);
            return;
        }

        for(int i = idx; i < list.size(); i++){
            vir[count] = list.get(i);
            combi(count+1,i+1);
        }
    }//combi end

    private static void spread(int total){
        Queue<Virus> que = new LinkedList<>();
        boolean[][] visited = new boolean[n][n];

        for(int i = 0; i < m; i++){
            Virus virus = vir[i];
            visited[virus.x][virus.y] = true;
            que.offer(new Virus(virus.x,virus.y,virus.time));
        }

        while(!que.isEmpty()){
            Virus now = que.poll();

            for(int d = 0; d < 4; d++){
                int nx = now.x + dx[d];
                int ny = now.y + dy[d];

                if(nx >= 0 && ny >= 0 && nx < n && ny < n && arr[nx][ny] != 1 && !visited[nx][ny]){
                    if(arr[nx][ny] == 0){
                        total--;
                    }

                    if(total == 0){
                        result = Math.min(result,now.time+1);
                        return;
                    }

                    visited[nx][ny] = true;
                    que.offer(new Virus(nx,ny,now.time+1));
                }
            }
        }
    }//spread end
}//class end
