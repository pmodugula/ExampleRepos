while(my$a=<>){
 next if($a=~m/^\s*#/);
 $a=~s/^(.*\S)\s*$/$1/;
 next if($a=~m/^\s*$/);
 $a=~s/^"(.*)"$/$1/;
 next if($a=~m/[A-Z]/&&$a=~m/[a-z]/);
 foreach my$b(split(/\s+/,$a)){
  $b=~m/(.)(.*)=(.{0,1})(.*)/;
  my@b=($1,$2,$3,$4);
  if($b[0]=~m/_/){
   print"SET \"".$b."\"\n";
  }else{
   print"SET \"_".uc($b[0].$b[1])."_=".uc($b[2].$b[3])."\"\n";
   print"SET \"_".uc($b[0]).lc($b[1])."__=".uc($b[2]).lc($b[3])."\"\n";
   print"SET \"__".lc($b[0].$b[1])."__=".lc($b[2].$b[3])."\"\n";
  }
 }
}