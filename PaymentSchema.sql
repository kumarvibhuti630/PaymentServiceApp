{\rtf1\ansi\ansicpg1252\cocoartf2513
\cocoatextscaling0\cocoaplatform0{\fonttbl\f0\fnil\fcharset0 Menlo-Regular;\f1\froman\fcharset0 Times-Roman;}
{\colortbl;\red255\green255\blue255;\red0\green0\blue0;\red0\green0\blue0;}
{\*\expandedcolortbl;;\csgray\c0;\cssrgb\c0\c0\c0;}
\paperw11900\paperh16840\margl1440\margr1440\vieww10800\viewh8400\viewkind0
\pard\tx560\tx1120\tx1680\tx2240\tx2800\tx3360\tx3920\tx4480\tx5040\tx5600\tx6160\tx6720\pardirnatural\partightenfactor0

\f0\fs22 \cf2 \CocoaLigature0 CREATE DATABASE /*!32312 IF NOT EXISTS*/Vibhuti/*!40100 DEFAULT CHARACTER SET latin1 */;\
USE Vibhuti;\
DROP TABLE IF EXISTS paymentmodeldto;
\f1\fs24 \cf3 \expnd0\expndtw0\kerning0
\CocoaLigature1 \outl0\strokewidth0 \strokec3 \
\pard\pardeftab720\partightenfactor0
\cf3 CREATE TABLE paymentmodeldto(id bigint PRIMARY KEY, created DATETIME(6), currency varchar(3), modified DATETIME(6), order_amount double, order_id varchar(100) UNIQUE, transaction_id varchar(100), transaction_status varchar(20), user_id varchar(100) ); \
\
\
}