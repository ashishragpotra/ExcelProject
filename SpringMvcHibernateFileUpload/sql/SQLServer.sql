USE [testDb]
GO

/****** Object:  Table [dbo].[geological_sections]    Script Date: 5/30/2019 5:54:15 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

SET ANSI_PADDING ON
GO

CREATE TABLE [dbo].[geological_sections](
	[id] [numeric](19, 0) IDENTITY(1,1) NOT NULL,
	[class1_code] [varchar](255) NULL,
	[class1_name] [varchar](255) NULL,
	[class2_code] [varchar](255) NULL,
	[class2_name] [varchar](255) NULL,
	[section_name] [varchar](255) NULL,
	[upload_id] [numeric](19, 0) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

SET ANSI_PADDING OFF
GO

ALTER TABLE [dbo].[geological_sections]  WITH CHECK ADD  CONSTRAINT [FK_pi6grx6nfnisc5e7iyjq6pvgk] FOREIGN KEY([upload_id])
REFERENCES [dbo].[uploadfile] ([id])
GO

ALTER TABLE [dbo].[geological_sections] CHECK CONSTRAINT [FK_pi6grx6nfnisc5e7iyjq6pvgk]
GO



---------------------------------------------------------------------------------------


USE [testDb]
GO

/****** Object:  Table [dbo].[uploadfile]    Script Date: 5/30/2019 5:54:37 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

SET ANSI_PADDING ON
GO

CREATE TABLE [dbo].[uploadfile](
	[id] [numeric](19, 0) IDENTITY(1,1) NOT NULL,
	[file_name] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

SET ANSI_PADDING OFF
GO










