# branch

- 브랜치 생성 : git branch 브랜치명
- 브랜치 이동 : git switch 브랜치명
- 브랜치 합치기 : 기준 브랜치 이동 후 git merge 브랜치명
- 충돌 해결 : 코드고치고 git add & git commit
- 현재 브랜치 확인 : git status
- 브랜치 그래프 보기 : git log —oneline —all —graph

# merge

1. 3-way merge

![image](https://github.com/NAINSOO/SSAFY11th-Gwangju04-WebStudy/assets/68545724/c478231f-5000-43d6-adff-4cc18b3ca925)

2. fast-forward merge
    1. fast-forward merge가 자동으로 이뤄지는 것을 방지 : git merge —no-ff 브랜치명
    2. merge 완료된 브랜치 삭제 : git branch -d 브랜치명
    3. merge 안한 브랜치 삭제 : git branch -D 브랜치명
![image](https://github.com/NAINSOO/SSAFY11th-Gwangju04-WebStudy/assets/68545724/98686251-9505-43b9-8047-ececcbb0df85)

3. git rebase & fast-forward merge 
    1. git log가 깔끔하게 보임
    2. conflict 엔딩 많이 나는 단점 존재.
    3. 새로운 브랜치로 이동해서 git rebase 중심브랜치명
    4. 중심 브랜치로 이동해서 git merge 새로운 브랜치 명
![image](https://github.com/NAINSOO/SSAFY11th-Gwangju04-WebStudy/assets/68545724/2fbda888-f2f3-45e7-a22a-17eb755c3689)


4. squash & git merge
    1. side branch의 log 기록을 삭제할 수 있다.
![image](https://github.com/NAINSOO/SSAFY11th-Gwangju04-WebStudy/assets/68545724/d842de29-dcb3-4b16-b66a-b88414df5e8b)

