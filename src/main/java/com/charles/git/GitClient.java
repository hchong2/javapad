package com.charles.git;

import java.io.File;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;

public class GitClient {

	/**
	 * @param args
	 */
	public static void main(String[] args)throws Exception
	{
		
		String location = "/home/charles/dev/workspace/scootchnews/.git";
		Repository repo = new FileRepositoryBuilder()
								.setGitDir(new File(location))
								.build();
		
		Git git = new Git(repo);
		
		for(RevCommit commit: git.log().call()){
			System.out.println(getCommitDetails(commit));
		}
//		Ref master = repo.getRef("master");
//		ObjectId masterTip = master.getObjectId();
		
//		System.out.println(masterTip.toString());
		
//		RevWalk walk = new RevWalk(repo);
//		RevCommit commit = walk.parseCommit(masterTip);
//		
//		System.out.println(getCommitDetails(commit));
//		
//		for(int i=1; i<10; i++){
//			RevCommit next = walk.next();
//			if(next != null){
//				System.out.println(getCommitDetails(next));
//			}
//		}
	}
	
	public static String getCommitDetails(RevCommit commit){
		
		StringBuilder s = new StringBuilder();
		s.append(commit.getAuthorIdent().getWhen());
//		s.append("\n"+commit.getCommitTime());
//		s.append("\n"+commit.getCommitterIdent());
//		s.append("\n"+commit.getShortMessage());
//		s.append("\n"+commit.getFullMessage());
		
		
		return s.toString();
	}

}
