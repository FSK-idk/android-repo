package co.feip.fefu2025.data.storage.repo_list

import co.feip.fefu2025.data.storage.RepoListStorage
import co.feip.fefu2025.data.storage.StabData
import co.feip.fefu2025.data.storage.dto.RepoDto
import kotlinx.coroutines.delay
import kotlin.random.Random

class RepoListStorageImpl : RepoListStorage {
    override suspend fun getStarredList(): List<RepoDto> {
        delay(1000L)
        if (Random.nextInt(0, 5) == 0) {
            throw Exception("Internal error")
        }

        return StabData.stabsRand
            .take(20)
            .map { it.repoDto }
    }

    override suspend fun getPopularList(): List<RepoDto> {
        delay(1000L)
        if (Random.nextInt(0, 5) == 0) {
            throw Exception("Internal error")
        }

        return StabData.stabsRand
            .take(30)
            .map { it.repoDto }
    }

    override suspend fun getRepoListByName(name: String): List<RepoDto> {
        delay(1000L)
        if (Random.nextInt(0, 5) == 0) {
            throw Exception("Internal error")
        }

        if (name.isEmpty())
            return listOf()

        return StabData.stabsRand
            .take(30)
            .map { it.repoDto }
            .filter { it.name.startsWith(name) }
            .sortedBy { it.name }
    }
}