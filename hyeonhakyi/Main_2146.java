package ex0401;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_2146 {
    private static class Node{
        int x;
        int y;
        int count;
        public Node(int x,int y,int count){
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }
    private static class Land{
        int x;
        int y;
        public Land(int x,int y){
            this.x = x;
            this.y = y;
        }
    }
    private static int n,result;
    private static int[] dx = {-1,1,0,0};
    private static int[] dy = {0,0,-1,1};
    private static int[][] arr;
    private static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        arr = new int[n][n];
        visited = new boolean[n][n];

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        landCheck();

        int answer = Integer.MAX_VALUE;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(arr[i][j] > 0){
                    visited = new boolean[n][n];
                    result = bridge(i,j);

                    if(result == -1){
                        continue;
                    }else{
                        answer = Math.min(result,answer);
                    }
                }
            }
        }
        System.out.println(answer);
    }//main end
    private static void landCheck(){
        int idx = 2;
        Queue<Land> que = new LinkedList<>();

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(arr[i][j] != 0 && !visited[i][j]){
                    que.offer(new Land(i,j));
                    arr[i][j] = idx;
                    visited[i][j] = true;

                    while(!que.isEmpty()){
                        Land now = que.poll();
                        int nowX = now.x;
                        int nowY = now.y;

                        for(int d = 0; d < 4; d++){
                            int nx = nowX + dx[d];
                            int ny = nowY + dy[d];

                            if(nx >= 0 && ny >= 0 && nx < n && ny < n && !visited[nx][ny]){
                                if(arr[nx][ny] == 1){
                                    arr[nx][ny] = idx;
                                    que.offer(new Land(nx,ny));
                                    visited[nx][ny] = true;
                                }
                            }
                        }
                    }
                    idx++;
                }
            }
        }
    }//landCheck end

    private static int bridge(int x, int y){
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(x,y,0));
        int num = arr[x][y];
        visited[x][y] = true;

        while(!q.isEmpty()){
            Node now = q.poll();
            int nowX = now.x;
            int nowY = now.y;
            int count = now.count;

            if(arr[nowX][nowY] != 0 && arr[nowX][nowY] != num) {
                return count - 1;
            }
                for(int d = 0; d < 4; d++){
                int nx = nowX + dx[d];
                int ny = nowY + dy[d];

                if(nx >= 0 && ny >= 0 && nx < n && ny < n && !visited[nx][ny] && arr[nx][ny] != num){
                    q.offer(new Node(nx,ny,count+1));
                    visited[nx][ny] = true;
                }
            }
        }
        return -1;
    }//bright end
}//class end
