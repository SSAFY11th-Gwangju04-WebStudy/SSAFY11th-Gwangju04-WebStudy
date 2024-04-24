import os
from datetime import datetime, timedelta
from github import Github

GITHUB_TOKEN = os.getenv('GITHUB_TOKEN')
REPO_NAME = 'SSAFY11th-Gwangju04-WebStudy/SSAFY11th-Gwangju04-WebStudy'

def generate_study_log():
    g = Github(GITHUB_TOKEN)
    repo = g.get_repo(REPO_NAME)
    
    # 전날 날짜로 파일명 및 필터링 설정
    yesterday = datetime.now() - timedelta(days=1)
    date_filter = yesterday.strftime("%Y-%m-%d")
    md_file_path = f"studyLog/{date_filter}_study_log.md"
    os.makedirs(os.path.dirname(md_file_path), exist_ok=True)
    
    with open(md_file_path, "w") as md_file:
        md_file.write(f"# Study Log for {date_filter}\n\n")
        md_file.write("|이름|날짜|스터디내용|\n")
        md_file.write("|---|---|---|\n")
        
        # 지정된 날짜에 생성된 커밋만 검색
        commits = repo.get_commits(since=yesterday, until=yesterday + timedelta(days=1))
        for commit in commits:
            commit_message = commit.commit.message
            if " / " in commit_message:
                try:
                    name, date, study_content = commit_message.split(' / ')
                    md_content = f"|{name}|{date}|{study_content}|\n"
                    md_file.write(md_content)
                except ValueError:
                    # 커밋 메시지 형식이 잘못된 경우 처리
                    continue

if __name__ == "__main__":
    generate_study_log()
