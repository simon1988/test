#shell
for file in `ls`;do sed -i -E 's/(.*com) (.*)/\2 \1/' $file; done

#git
git reset --hard <commit_id>
git push origin HEAD:<branch> --force
git checkout -b <branch> <origin/branch>
git push origin -u(--set-upstream) <branch>
git push origin :<branch> 把一个空分支push到server上，相当于删除该分支
git clean -f -d
git merge --squash <branch> 多次commit合成一个
git format-patch A..B --> git am *.patch
git cherry-pick develop/cherry-pick A..B

