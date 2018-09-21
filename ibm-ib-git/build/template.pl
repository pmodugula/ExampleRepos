#!perl
# File: template.pl
# Version: 0.1.2
# Date: 13-Nov 2014
# Author: Alex Russell

my($rc,$ns,$envn)=(0,'_',{});
sub replace{
 my($line,$ns,$i,$file,$repl)=('',shift,shift,shift,shift);
 while($repl=~m/(.*?)\${(.+?)}.*/){
  my($_1,$_2,$nameOrKey,$keyOrVal)=($1,$2,undef,undef);
  if($_2=~m/(.*):(.*)/){
   ($nameOrKey,$keyOrVal)=($1,$2);$_ns=$1;
   if($^O=~/Win/){
    $nameOrKey=~s/\//\\/g;
   }else{
    $nameOrKey=~s/\\/\//g;
   }
   ## was $ns instead of $nameOrKey
   local*IN;
   if(open(IN,"<$nameOrKey")){
    close(IN);
    process($nameOrKey,-1,$nameOrKey);
   }
  }else{
   ($nameOrKey,$keyOrVal)=($ns,$_2);
  }
  ## was $ns instead of $nameOrKey
  if(defined($envn->{$nameOrKey}->{$keyOrVal})){
   $_2=$envn->{$nameOrKey}->{$keyOrVal}; ## was $ns instead of $nameOrKey
  }elsif(defined($envn->{'_'}->{$nameOrKey})){
   $_2=$envn->{'_'}->{$nameOfKey};
  }elsif(defined($envn->{'_'}->{$keyOrVal})){
   $_2=$envn->{'_'}->{$keyOrVal};
  }elsif(defined($ENV{$keyOrVal})){
   $_2=$ENV{$keyOrVal};
  }elsif(defined($keyOrVal)){
   $_2=$keyOrVal;
  }else{
   $_2="\${$_2}";
  }
  $line.="$_1$_2";
  $repl=~s/.*?\${.+?}(.*)/$1/;
 }
 return"$line$repl";
}
while(<STDIN>){
 next if(m/^#/ && $ENV{_KEEP_COMMENTED_LINES}ne'Y');
 s/(.*\S)\s*/$1/;
 next if(m/^\s*$/ && $ENV{_KEEP_BLANK_LINES}eq'N');
 if(m/(.*?)\="{0,1}(.*?)"/){
 }elsif(m/(.*?)\="{0,1}(.*)"{0,1}/){
 }
 my$repl=replace($ns,-1,undef,$2);
 $envn->{$ns}->{$1}=$repl;
}
sub process{
 my($__,$ns,$i,$file)=(undef,shift,shift,shift);
 if($^O=~/Win/){
  $file=~s/\//\\/g;
 }else{
  $file=~s/\\/\//g;
 }
 if(!defined($envn->{$file}->{_})){
  $envn->{$file}->{_}=1;
  local*IN;
  if(open(IN,"<$file")){
   while(<IN>){
    next if(m/^#/ && $ENV{_KEEP_COMMENTED_LINES}ne'Y');
    s/(.*\S)\s*/$1/;
    next if(m/^\s*$/ && $ENV{_KEEP_BLANK_LINES}eq'N');
    my$z=0;
    if(m/(.*?)\="{0,1}(.*?)"/){
     $z=1;
    }elsif(m/(.*?)\="{0,1}(.*)"{0,1}/){
     $z=1;
    }
    if(1==$z){
     my($_1,$_2)=($1,$2);
     my$repl=replace($ns,$i,$file,$_2);
     $envn->{$ns}->{$_1}=$repl;
    }
    if('_'eq$ns&&0==$i){
     my$repl=replace($ns,$i,$file,$_);
     $repl=~s/(.*\S)\s*/$1/;
     print STDOUT $repl."\n";
    }
   }
   close(IN);
  }else{
   print STDERR "Error: failed to open $file for reading\n";
  }
 }
}
for(my$i=@ARGV-1;$i>=0;$i--){
 process($ns,$i,$ARGV[$i]);
}
exit($rc);