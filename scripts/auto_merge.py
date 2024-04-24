import os
from datetime import datetime, timedelta
from github import Github

GITHUB_TOKEN = os.getenv('GITHUB_TOKEN')
REPO_NAME = 'SSAFY11th-Gwangju04-WebStudy/SSAFY11th-Gwangju04-WebStudy'

g = Github(GITHUB_TOKEN)
repo = g.get_repo(REPO_NAME)

def label_and_merge_prs():
    yesterday = (datetime.now() - timedelta(1)).strftime('%y%m%d')
    open_prs = repo.get_pulls(state='open')
    for pr in open_prs:
        if yesterday in pr.title:
            pr.add_to_labels('automerge')
            pr = repo.get_pull(pr.number)  # PR 객체를 새로고침
            print(f"Added automerge label to PR #{pr.number}")
            if 'automerge' in [label.name for label in pr.get_labels()]:  # 라벨을 다시 확인
                if pr.mergeable:
                    pr.merge(merge_method='merge', commit_title=pr.title)
                    print(f"Merged PR #{pr.number} successfully")
                else:
                    print(f"PR #{pr.number} is not mergeable due to conflicts or repo rules.")

if __name__ == '__main__':
    label_and_merge_prs()
