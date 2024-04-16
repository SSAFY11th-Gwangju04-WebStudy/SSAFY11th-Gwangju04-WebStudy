import os
from datetime import datetime, timedelta
from github import Github

# GitHub 인증을 위한 토큰 설정
GITHUB_TOKEN = os.getenv('GITHUB_TOKEN')
REPO_NAME = 'SSAFY11th-Gwangju04-WebStudy/SSAFY11th-Gwangju04-WebStudy'

# GitHub 객체 초기화
g = Github(GITHUB_TOKEN)
repo = g.get_repo(REPO_NAME)

def label_and_merge_prs():
    # 전날 날짜 계산
    yesterday = (datetime.now() - timedelta(1)).strftime('%y%m%d')

    # 전날 날짜를 포함하는 제목을 가진 오픈된 PRs 검색
    open_prs = repo.get_pulls(state='open')
    for pr in open_prs:
        if yesterday in pr.title:
            # 'automerge' 라벨 추가
            pr.add_to_labels('automerge')
            print(f"Added automerge label to PR #{pr.number}")

            # PR 병합 조건 검사 및 병합 실행
            if 'automerge' in [label.name for label in pr.labels]:
                if pr.mergeable:  # 병합 가능한 상태인지 확인
                    pr.merge(merge_method='merge', commit_title=pr.title)
                    print(f"Merged PR #{pr.number} successfully")

if __name__ == '__main__':
    label_and_merge_prs()
