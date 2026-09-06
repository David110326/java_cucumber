groovy
pipeline {
    // 指定此流水线在任何可用的 Jenkins 代理节点上运行
    agent any

    stages {
        // 阶段1：从 Git 仓库拉取你的自动化测试代码
        stage('Checkout') {
            steps {
                echo '开始从 GitHub 拉取代码...'
                // 这个 git 步骤会从配置好的仓库和分支拉取代码
                git branch: 'master', url: 'https://github.com/David110326/java_cucumber.git'
            }
        }

        // 阶段2：执行测试
        stage('Run Tests') {
            steps {
                echo '开始运行 Cucumber 测试...'
                // 在 Jenkins 的构建节点上执行 Maven 命令来运行测试
                // 这会调用 pom.xml 中配置的 cucumber 测试套件
                sh 'mvn clean test'
            }
        }

        // 阶段3：归档测试报告（可选但强烈推荐）
        stage('Archive Test Report') {
            steps {
                echo '归档测试报告...'
                // 收集并保存 Cucumber 生成的 HTML 报告，方便在 Jenkins 上查看
                publishHTML([
                    reportDir: 'target/cucumber-reports',
                    reportFiles: 'cucumber.html',
                    reportName: 'Cucumber Test Report'
                ])
            }
        }
    }

    // 构建后操作：无论成功或失败，都清理工作空间以节省磁盘空间
    post {
        always {
            cleanWs()
        }
    }
}