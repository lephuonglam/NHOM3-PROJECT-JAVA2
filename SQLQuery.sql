Create database [LABOR-MANAGEMENT] 

USE [LABOR-MANAGEMENT] 

CREATE TABLE [dbo].[WORKER](

	[db_name] [varchar](50)  PRIMARY KEY NOT NULL,
	[db_address] [varchar](50) NOT NULL,
	[db_status] [varchar](50) NULL,
	[db_weight] [float] NULL,
	[db_height] [float] NULL,
	[db_phone] [int] NULL

)
