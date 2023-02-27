# Flyme WebApp

---
### Airline Reservation System JavaEE WebApp 
Group 6 Final Project for CSD 4464 – Programming Java EE Term 2023W 


## The GitHub Work Flow
The Simplified Git Flow with GitHub. How is it going to work?
![GitHub Workflow](https://github.com/hbeltrane/Assignment2/blob/main/src/main/webapp/images/github-workflow.jpg)
## 1. Initialize the local repository
Once a centralized remote repository is created on GitHub, we have to clone it on our local directory to
create a reference to the project in our local computer.

That can be done with "Open a project from remote repository" option in Eclipse or typing the following 
command in a window terminal:

```shell
git clone <repository URL>
```

### 2. Set global configuration
After done that, with the terminal open rooted to project local directory, we set the global configuration
variables, name and email. The same email we used to create our GitHub account. This way our local repository
will set our authentication credentials when we try to push changes to the remote repository.

```shell
git config --global user.name "<Your Name>"
```
```shell
git config --global user.email "<your email>"
```

### 3. Set up the Working directory
We rename our local master branch to "main".
```shell
git branch -M main
```

### 4. Make changes and commit
To avoid making changes directly to the main branch we have to create a new branch with a meaningful name 
and switch to work on it. The new features that we are going to be adding to the project are all changed 
there. Then, when finnish that feature, we commit to save them.

To create and switch to a new branch:
```shell
git branch <branch name>
```
```shell
git switch <branch name>
```
or simply in one command
```shell
git switch -c <branch name>
```

So, here is where you star working with your part of the project. After you finish with all your files, then you add
and commit those changes.

To add all modified files to the staging area.

```shell
git add --all
```
or 
```shell
git add .
```

If we want to see the status of the files that have been modified on the current branch or waiting to be committed 
we can run.
```shell
git status 
```

To save the changes we execute the following command including a comment that summarize the changes been 
made.
```shell
git commit -m "A descriptive message"
```

### 5. Merge the feature branch
After we finnish making changes on the feature branch and tested the new changes are working well in our local
machine, we have to merge those new changes to the main branch. For that, we switch to the main branch first:
```shell
git switch main
```
And on the main branch we merge the feature branch back to main:
```shell
git merge <branch name>
```
Finally, we delete the branch we don't need anymore:
```shell
git branch --delete <branch name>
```

### 6. Push new commits to remote repository
We all our changes saved on our local version of the main branch, now we would need to synchronize the remote 
and most updated repository to our local one. So that, if someone has pushed changes to the remote main branch 
before, we get those changes first in our local main branch before upload our changes to that GitHub 
repository.

To download, update and join references on the GitHub with our local repository:
```shell
git pull
```
And when everything is merged, we test again our code but in the main branch. We may sure that everything is
working as should be. After all done, we can finally send back all changes together unto GitHub from our 
main branch:
```shell
git pull
```

### 7. Managing conflicts
**IMPORTANT ⚠️** If any conflict emerge in any of the previous steps, we must locate source of conflict and solve it.
The remote repository represents the official project, so its commit history should be treated as sacred and 
immutable. If a one of our local commits diverge from the main branch, We should not push those changes 
because this would overwrite official commits. Again, we first solve the conflict and later continue with the merge.

The following command can only be run after a merge attempt has resulted in conflicts.
```shell
git merge --continue
```

### 8. Start over again
After we finish the previous series of changes, we can start all over again but with a new feature. For that 
we just need to repeat process from step **4** and on.

To see commits history we can type the commands:
```shell
git log
```
or
```shell
git log --oneline
```
We can all see all our branches in our local repository with:
```shell
git branch 
```
Or list all local and remote branches:
```shell
git branch -a
```
