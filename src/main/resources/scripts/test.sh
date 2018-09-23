#shell
for file in `ls`;do sed -i -E 's/(.*com) (.*)/\2 \1/' $file; done
tail tmp.txt |cut -c1-19|grep "2018-05-29"|sort -k2|uniq -c
tail tmp.txt |sed -E 's/.*userId=(2266[0-9]+);.*/\1/'|sort -k1|uniq -c
cat 2.txt |awk '{print $1}'|sort -k1|uniq -c|sort -nk2

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

git tag -a 1.5.1 -m 'WebSite version 1.5.1'
git push origin :refs/tags/1.5.1
git push origin 1.5.1