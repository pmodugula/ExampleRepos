#!/usr/bin/env bash
echo _REMOVE: $_SHELL_BROKADM "sudo su - dbkadm -c 'rm -f $1'"
$_SHELL_BRKADM "sudo su - dbkadm -c 'rm -f $1'"