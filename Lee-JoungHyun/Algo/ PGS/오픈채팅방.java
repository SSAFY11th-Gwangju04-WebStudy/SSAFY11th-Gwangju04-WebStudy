import java.util.*;

class Solution {
    public String[] solution(String[] record) {
    
        StringTokenizer st;
        // 상태, uuid 저장 배열 (1은 입장, 2는 퇴장)
        List<String[]> ans = new LinkedList();
        
        // 유저 아이디: 닉네임
        HashMap<String, String> map = new HashMap();
        for (int i = 0; i < record.length; i++) {
            st = new StringTokenizer(record[i]);
            
            String doing = st.nextToken();
            
            if (doing.equals("Enter")) {
                
                String code = st.nextToken();
                ans.add(new String[] {"1", code});
                map.put(code, st.nextToken());
                
            } 
            else if (doing.equals("Leave")) {   
                ans.add(new String[] {"0", st.nextToken()});
            } 
            else {
                String code = st.nextToken();
                map.put(code, st.nextToken());
            }
        }
        
        String[] answer = new String[ans.size()];
        int idx = 0;
        for (String[] s : ans) {
            String tmp = map.get(s[1]);
            if (s[0] == "1") {
                tmp += "님이 들어왔습니다.";
            } else {
                tmp += "님이 나갔습니다.";
            }
            
            answer[idx++] = tmp;
        }
        return answer;
    }
}
