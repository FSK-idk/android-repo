package co.feip.fefu2025.data.storage.repo_page

import co.feip.fefu2025.data.storage.RepoPageStorage
import co.feip.fefu2025.data.storage.StabData
import co.feip.fefu2025.data.storage.dto.RepoPageDto
import kotlinx.coroutines.delay
import kotlin.random.Random

class RepoPageStorageImpl : RepoPageStorage {
    override suspend fun get(id: Int): RepoPageDto {
        delay(1000L)
        if (Random.nextInt(0, 2) == 1) {
            throw Exception("Internal error")
        }

        return StabData.stabs[id].repoPageDto
    }
}