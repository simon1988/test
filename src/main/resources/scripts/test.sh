#extract password
for user in `cat users.txt |sed 's/@.*//'`;do dlog -d 0824 $user|head -2>>userpass.txt; done
sed -i '/====/d' userpass.txt
sed -i 's/.*email:\(.*\.com\),phone.*password:\(.*\),passwordFund.*/\1 \2/' userpass.txt
sed -i 's/\(.*\.com\) \(.*\)/\2 \1/' userpass.txt
#upload & reupload
log ImageUpload|sed 's/.*userName:\(.*\),file1.*/\1/'|sed '/ImageController/d'|uniq >upload.txt
log ImageReUpload|sed 's/.*userName:\(.*\),file1.*/\1/'|sed '/ImageController/d'|uniq >reupload.txt
#extract username
log -d1006 '"429"'|sed 's/.*429","retDesc":"\(.*\)"}/\1/'|sort|uniq -c|sed '/===/d'|sort -n -k 1
#openacc log
dlog -a40 -f tcp -d 0825 13662411113
#ab test
ab -n 1200 -c 120 "http://fa.163.com/interfaces/mobapp/trade/account/partnerLogin.do?systemVersion=2.3.5&apiLevel=8&deviceType=android&systemName=HTC%20Incredible%20S&jysId=njs&productVersion=2.8.1&channel=tgwbaidugjs&uniqueId=358000047348956&login_id=3B0DC95E3122EAEC54B4C2B15F471D6BBDA1ED74943CEE78C25A3BA3DCEBF0C8B68DFFB0D7B213C14CF5C4FA6CA7EADD&params=A1470121D86261D2B76C44BF5E7EBD8E1721FA4BE72016DE4747BB9B946B32440C85A8C98CA5E459314B70BAE3437A2F6C4CE527196AD6FD438303C54C76D9B502F41516B9AF69EE2C584003F863B77A7AE9A6CC1FAA37CE6EFCE178A8B2D726D55253DA485005B0F926E281B6CFDD6A4E7B7C2E823D6962F617476DBB24C7785C7704806120EC0A4680174C165A2018DA9F158BCCD3D8C955709EB58250392CC8F40AA53C4F0CE521F7BE48D290DD783E7E7A7D7603BA4338FD9BC181BF98021C8A2933A35D43445023BA9AF8B50C656DC987BE0D6F191A313272636E6C0B03"
#grep log
grep e43e886854e3a5a2df919fa46e6ca8cf /home/project/fa.163.com/*/log/access_debug_log.log
bzgrep kongyanning3610 /home/project/logArchive/10.120.118.*/2015/06/30/home/project/*fa.163.com/log/openacc.log.2015-06-30.bz2
bzgrep jy0215 /home/project/logArchive/10.120.118.*/2015/08/25/home/project/qz.fa.163.com/logs/quanziLog.log.2015-08-25.bz2
bzgrep 1080966279 /home/project/logArchive/10.120.118.7*/2015/08/13/home/project/fa.ms.netease.com/log/silver.log.2015-08-13.bz2
#tail log
tail -f /home/project/fa.163.com/dev_API/log/access_debug_log.log
tail -f /home/project/fanew.ms.netease.com/dev_API/logs/localhost.2015-10-21.log
#curl set
curl "http://10.120.45.64:8002/bg/cron/call/LogAnalysisCron.html"
curl "http://10.120.118.172:8001/bg/cron/call/SyncPhotoWithSgeCron.html"
curl "http://10.120.118.87:8000/tools/cache/delete?userName=bjtsh@126.com&type=profile"
curl "http://10.120.118.87:8000/tools/removeTemplateCache.do?templateNames=ftl"
#rz & sz
rz -bye
#memcached
telnet sess.mem.fa.163.com 11011, info.mem.fa.163.com 11012//64 profile_
telnet sess.mem.fa.163.com:22411:33411, info.mem.fa.163.com:22412:33412//87 
#redis
redis-cli -h 10.120.42.56 -p 6386 -a 1qazwwwtxtfa//64zhuzhan
/home/redis/bin/redis-cli -h 10.120.118.117 -p 6380 -a QAZplxxzz_qzpmfa//94zhuzhan
/home/redis/bin/redis-cli -h 10.120.118.117 -p 6385 -a QAZplxxzz_qzfa//94quanzi
SENTINEL get-master-addr-by-name master
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
#
./configure --prefix=/usr --libdir=/usr/lib64
yum install openssl-devel
export LD_LIBRARY_PATH=~/gg/lib
export LISTEN_PORT=49000
nohup bin/gg >log/gg.log 2>&1 &