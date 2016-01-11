     ,-----.,--.                  ,--. ,---.   ,--.,------.  ,------.
    '  .--./|  | ,---. ,--.,--. ,-|  || o   \  |  ||  .-.  \ |  .---'
    |  |    |  || .-. ||  ||  |' .-. |`..'  |  |  ||  |  \  :|  `--, 
    '  '--'\|  |' '-' ''  ''  '\ `-' | .'  /   |  ||  '--'  /|  `---.
     `-----'`--' `---'  `----'  `---'  `--'    `--'`-------' `------'
    ----------------------------------------------------------------- 


References:

    http://www.mono-project.com/docs/getting-started/install/linux/
    http://www.mono-project.com/docs/getting-started/mono-basics/
    http://stackoverflow.com/questions/25784490/does-cloud-9-support-net-for-build-or-deploy-or-debug

Install:

    sudo apt-key adv --keyserver keyserver.ubuntu.com --recv-keys 3FA7E0328081BFF6A14DA29AA6A19B38D3D831EF
    echo "deb http://download.mono-project.com/repo/debian wheezy main" | sudo tee /etc/apt/sources.list.d/mono-xamarin.list
    sudo apt-get update
    sudo apt-get update --fix-missing
    sudo apt-get install mono-devel
    sudo apt-get install mono-complete
    sudo apt-get install referenceassemblies-pcl