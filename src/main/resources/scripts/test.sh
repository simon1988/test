#extract password
for user in `cat users.txt |sed 's/@.*//'`;do dlog -d 0824 $user|head -2>>userpass.txt; done
sed -i '/====/d' userpass.txt
sed -i 's/.*email:\(.*\.com\),phone.*password:\(.*\),passwordFund.*/\1 \2/' userpass.txt
sed -i 's/\(.*\.com\) \(.*\)/\2 \1/' userpass.txt
#upload & reupload
log ImageUpload|sed 's/.*userName:\(.*\),file1.*/\1/'|sed '/ImageController/d'|uniq >upload.txt
log ImageReUpload|sed 's/.*userName:\(.*\),file1.*/\1/'|sed '/ImageController/d'|uniq >reupload.txt
#
