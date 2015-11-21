prj=$1
cp -r skeleton $prj
mv $prj/NAME.gemspec $prj/$prj.gemspec
mv $prj/bin/NAME $prj/bin/$prj
mv $prj/tests/test_NAME.rb $prj/tests/test_$prj.rb
mv $prj/lib/NAME $prj/lib/$prj
mv $prj/lib/NAME.rb $prj/lib/$prj.rb
find ./$prj -name "*NAME*" -print
