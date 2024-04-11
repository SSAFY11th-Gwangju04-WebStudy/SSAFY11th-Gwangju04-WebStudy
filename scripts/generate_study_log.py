import os
from datetime import datetime
from github import Github

GITHUB_TOKEN = os.getenv('GITHUB_TOKEN')
REPO_NAME = 'SSAFY11th-Gwangju04-WebStudy/SSAFY11th-Gwangju04-WebStudy'

def generate_study_log():
    g = Github(GITHUB_TOKEN)
    repo = g.get_repo(REPO_NAME)
    
    # 오늘 날짜로 파일명 설정
    today = datetime.now().strftime("%Y%m%d")
    md_file_path = f"studyLog/{today}_study_log.md"
    os.makedirs(os.path.dirname(md_file_path), exist_ok=True)
    
    with open(md_file_path, "a") as md_file:
        md_file.write(f"# {today} Study Log\n\n")
        
        # 최근 커밋들 가져오기
        commits = repo.get_commits()
        for commit in commits:
            commit_message = commit.commit.message
            # 커밋 메시지가 지정된 형식에 맞는지 확인
            if " / " in commit_message:
                try:
                    name, date, study_content = commit_message.split(' / ')
                    # Markdown 파일에 내용 추가
                    md_content = f"- **Name**: {name}\n- **Date**: {date}\n- **Study Content**: {study_content}\n\n"
                    md_file.write(md_content)
                except ValueError:
                    # 커밋 메시지 형식이 잘못된 경우 건너뛰기
                    continue

if __name__ == "__main__":
    generate_study_log()
